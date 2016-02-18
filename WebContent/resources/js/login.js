/**
 * Created by mariusz on 18.02.16.
 */
$(document).ready(function () {

    $('#login-form-link').click(function (e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
});

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
                alert("You have created account!");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.responseText);
            }
        });

    } else {
        alert("Passwords are not equal!");
    }
};