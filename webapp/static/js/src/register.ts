/**
 * Created by mariusz on 17.05.16.
 */
///<reference path="apiUrl.ts"/>


module TwitterClone.Register {

    export function register(usernameId:string, passwordFieldId:string, repasswordFieldId:string, emailFieldId) {

        var username = getElementValue(usernameId);
        var password = getElementValue(passwordFieldId);
        var repassword = getElementValue(repasswordFieldId);
        var email = getElementValue(emailFieldId);

        if (password === repassword) {
            $.ajax({
                type: "post",
                url: Urls.createUser,
                data: JSON.stringify({"username": username, "password": password, "email": email}),
                headers: {"Content-Type": "application/json"},
                success: function (response) {
                    username = "";
                    password = "";
                    repassword = "";
                    email = "";
                    alert("You have created account! " + response);
                },
                error: function (xhr) {
                    console.log(xhr);
                    alert(xhr.responseJSON.message);
                }
            });

        } else {
            alert("Passwords are not equal!");
        }
    }

    function getElementValue(elemendId:string) {
        return (<HTMLInputElement>document.getElementById(elemendId)).value;
    }
}

