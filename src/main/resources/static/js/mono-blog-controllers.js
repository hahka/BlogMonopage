var monoBlogControllers = angular.module('monoBlogControllers', []);

monoBlogControllers.service('myService', function ($http) {
    var result;
    return {
        getHTML: function () {
            return $http({
                method: 'GET',
                url: 'newpost'
            }).success(function (data) {
                result = data;
            });
        }
    };
});


monoBlogControllers.factory('myServiceAsync', function ($http) {

    var urlCall = "posts";
    var getHTML = function () {
        return $http({method: "GET", url: urlCall}).then(function (result) {
            return result.data;
        });
    };

    getHTML.setUrl = function (url) {
        urlCall = url;
    }

    return {getHTML: getHTML};
});


monoBlogControllers.factory('appScopes', function ($rootScope) {
    var mem = {};

    return {
        store: function (key, value) {
            mem[key] = value;
        },
        get: function (key) {
            return mem[key];
        }
    };
});

// I provide a request-transformation method that is used to prepare the outgoing
// request as a FORM post instead of a JSON packet.
monoBlogControllers.factory(
    "transformRequestAsFormPost",
    function () {
        // I prepare the request data for the form post.
        function transformRequest(data, getHeaders) {
            var headers = getHeaders();
            headers["Content-type"] = "application/x-www-form-urlencoded; charset=utf-8";
            return ( serializeData(data) );
        }

        // Return the factory value.
        return ( transformRequest );
        // ---
        // PRVIATE METHODS.
        // ---
        // I serialize the given Object into a key-value pair string. This
        // method expects an object and will default to the toString() method.
        // --
        // NOTE: This is an atered version of the jQuery.param() method which
        // will serialize a data collection for Form posting.
        // --
        // https://github.com/jquery/jquery/blob/master/src/serialize.js#L45
        function serializeData(data) {
            // If this is not an object, defer to native stringification.
            if (!angular.isObject(data)) {
                return ( ( data == null ) ? "" : data.toString() );
            }
            var buffer = [];
            // Serialize each key in the object.
            for (var name in data) {
                if (!data.hasOwnProperty(name)) {
                    continue;
                }
                var value = data[name];
                buffer.push(
                    encodeURIComponent(name) +
                    "=" +
                    encodeURIComponent(( value == null ) ? "" : value)
                );
            }
            // Serialize the buffer and clean it up for transportation.
            var source = buffer
                    .join("&")
                    .replace(/%20/g, "+")
                ;
            return ( source );
        }
    }
);

monoBlogControllers.directive('dynamic', function ($compile) {
    return {
        replace: true,
        link: function (scope, ele, attrs) {
            scope.$watch(attrs.dynamic, function (html) {
                if (!html) {
                    return;
                }
                ele.html((typeof(html) === 'string') ? html : html.data);
                $compile(ele.contents())(scope);
            });
        }
    };
});

monoBlogControllers.controller('MainController', ['$scope', '$http', '$sce', '$window', 'myServiceAsync', 'appScopes',
        function ($scope, $http, $sce, $window, myServiceAsync, appScopes) {

            appScopes.store('MainController', $scope);

            /*$http.get('/posts').success(function (data) {
                data = $sce.trustAsHtml(data);
                $scope.mainView = data;
             });*/
            myServiceAsync.getHTML.setUrl("posts");
            var myDataPromise = myServiceAsync.getHTML();
            myDataPromise.then(function (result) {  // this is only run after $http completes
                $scope.mainDynamic = result;
            });

            $scope.addNewPostForm = function () {

                myServiceAsync.getHTML.setUrl("newpost");
                var myDataPromise = myServiceAsync.getHTML();
                myDataPromise.then(function (result) {  // this is only run after $http completes
                    $scope.mainDynamic = result;
                });

            };

            $scope.postDetails = function (e) {

                myServiceAsync.getHTML.setUrl("postdetails/" + $(e.target).attr("id"));
                var myDataPromise = myServiceAsync.getHTML();
                myDataPromise.then(function (result) {  // this is only run after $http completes
                    $scope.mainDynamic = result;
                });

            }

            $scope.addNewPostComment = function (e) {

                myServiceAsync.getHTML.setUrl("newcomment/" + $(e.target).attr("id"));
                var myDataPromise = myServiceAsync.getHTML();
                myDataPromise.then(function (result) {  // this is only run after $http completes
                    $scope.mainDynamic = result;
                });

            };

        }]
);

monoBlogControllers.controller('NewPostController',
    ['$scope', '$rootScope', '$http', '$sce', 'transformRequestAsFormPost', 'appScopes', 'myServiceAsync',
        function ($scope, $rootScope, $http, $sce, transformRequestAsFormPost, appScopes, myServiceAsync) {
            $scope.submit = function () {

                var title = $scope.form.title;
                var categoryId = $scope.form.categoryId;
                var content = $scope.form.content;
                if (title && content && categoryId) {

                    var req = {
                        method: 'POST',
                        url: 'newpostNg',
                        transformRequest: transformRequestAsFormPost,
                        data: {
                            title: title,
                            categoryId: categoryId,
                            content: content
                        }
                    }
                    $http(req).then(
                        function successCallback(response) {

                            myServiceAsync.getHTML.setUrl("posts");
                            var myDataPromise = myServiceAsync.getHTML();
                            myDataPromise.then(function (result) {  // this is only run after $http completes
                                appScopes.get('MainController').mainDynamic = result;
                            });

                        }, function errorCallback(response) {
                        });

                } else {
                    $window.alert("ko");
                }

            };
        }]
);


monoBlogControllers.controller('NewCommentController',
    ['$scope', '$rootScope', '$http', '$sce', 'transformRequestAsFormPost', 'appScopes', 'myServiceAsync',
        function ($scope, $rootScope, $http, $sce, transformRequestAsFormPost, appScopes, myServiceAsync) {
            $scope.submit = function (e) {

                var content = $scope.form.content;
                if (content) {

                    var req = {
                        method: 'POST',
                        url: 'newcommentNg',
                        transformRequest: transformRequestAsFormPost,
                        data: {
                            content: content,
                            postId: $(e.target).attr("id"),
                            userId: null,
                            date: null,
                            validated: null
                        }
                    }
                    $http(req).then(
                        function successCallback(response) {

                            myServiceAsync.getHTML.setUrl("postdetails/" + $(e.target).attr("id"));
                            var myDataPromise = myServiceAsync.getHTML();
                            myDataPromise.then(function (result) {  // this is only run after $http completes
                                appScopes.get('MainController').mainDynamic = result;
                            });

                        }, function errorCallback(response) {

                            myServiceAsync.getHTML.setUrl("postdetails/" + $(e.target).attr("id"));
                            var myDataPromise = myServiceAsync.getHTML();
                            myDataPromise.then(function (result) {  // this is only run after $http completes
                                appScopes.get('MainController').mainDynamic = result;
                            });

                        });

                } else {
                    $window.alert("ko");
                }

            };
        }]
);


