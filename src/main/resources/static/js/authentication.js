function verifyAuthentication(data) {
    var aud = "307924988327";
    if (data.aud.substring(0, aud.length) === aud)
        return true;
    else
        return false;
}