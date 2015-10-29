var monoBlogControllers = angular.module('monoBlogControllers', []);

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
    ['$scope', '$rootScope', '$http', '$sce', '$window', 'transformRequestAsFormPost', 'appScopes', 'myServiceAsync',
        function ($scope, $rootScope, $http, $sce, $window, transformRequestAsFormPost, appScopes, myServiceAsync) {
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
                    };
                    $http(req).then(
                        function successCallback(response) {

                            myServiceAsync.getHTML.setUrl("posts");
                            var myDataPromise = myServiceAsync.getHTML();
                            myDataPromise.then(function (result) {
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


monoBlogControllers.controller('NewUserController',
    ['$scope', '$rootScope', '$http', '$sce', '$window', 'transformRequestAsFormPost', 'myServiceAsync',
        function ($scope, $rootScope, $http, $sce, $window, transformRequestAsFormPost, myServiceAsync) {
            $scope.submit = function (e) {

                var username = $scope.form.username;
                var email = $window.userEmail;

                if (username) {

                    var req = {
                        method: 'POST',
                        url: 'newuserNg',
                        transformRequest: transformRequestAsFormPost,
                        data: {
                            username: username,
                            email: email
                        }
                    }
                    $http(req).then(
                        function successCallback(response) {

                            myServiceAsync.getHTML.setUrl("userLoggedIn/" + email + ".email");
                            var myDataPromise = myServiceAsync.getHTML();
                            myDataPromise.then(function (result) {
                                $('#user_info').html(result);
                            });

                        }, function errorCallback(response) {

                        });

                } else {
                    $window.alert(username);
                    $window.alert(email);
                    $window.alert("ko");
                }

            };
        }]
);


