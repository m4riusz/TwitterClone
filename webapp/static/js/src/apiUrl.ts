/**
 * Created by mariusz on 13.04.16.
 */

module TwitterClone.Urls {

    export const getAllUsers = `/rest/user`;
    export const getCurrentUser = `/rest/user/current`;
    export const getUserById = (userId:number)=>`/rest/user/${userId}`;
    export const getFollowersFromUserByUserId = (userId:number) => `/rest/user/followers/${userId}`;
    export const getFollowingUsersFromUserByUserId = (userId:number) => `/rest/user/following/${userId}`;

    export const editUserPassword = `/rest/user`;
    export const editUserRole = `/rest/user`;
    export const editUserEnable = (userId:number)=>`/rest/user/${userId}`;
    export const createUser = `/register`;
    export const followUser = `/rest/user/followers`;
    export const unfollowUser = `/rest/user/followers`;
}