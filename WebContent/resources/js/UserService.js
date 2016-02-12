/**
 * Created by mariusz on 12.02.16.
 */

app.factory("UserService", function ($http) {

    return {
        getAllUsers: function () {
            return $http({
                method: "get",
                url: "/rest/user"
            });
        },

        getUserById: function (userId) {
            return $http({
                method: "get",
                url: "/rest/user/" + userId
            });
        },

        follow: function (user) {
            return $http({
                method: "post",
                url: "/rest/user/following",
                data: JSON.stringify(user),
                headers: {"Content-Type": "application/json"}
            });
        },

        unfollow: function (user) {
            return $http({
                method: "delete",
                url: "/rest/user/following",
                data: JSON.stringify(user),
                headers: {"Content-Type": "application/json"}
            });
        },

        getFollowers: function () {
            return $http({
                method: "get",
                url: "/rest/user/followers"
            });
        },

        getFollowingUsers: function () {
            return $http({
                method: "get",
                url: "/rest/user/following"
            });
        }

    }
});