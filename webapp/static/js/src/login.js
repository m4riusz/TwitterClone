/**
 * Created by mariusz on 30.04.16.
 */
var register = function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var repassword = $("#confirm-password").val();
    var email = $("#email").val();

    if (password === repassword) {
        $.ajax({
            type: "post",
            url: "/register",
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
};