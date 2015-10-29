function verifyAuthentication(data) {
    var aud = "307924988327";
    return data.aud.substring(0, aud.length) === aud;
}