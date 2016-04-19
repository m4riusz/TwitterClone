/**
 * Created by mariusz on 13.04.16.
 */

module TwitterClone.Urls {

    export const getAllUsers = `/rest/user`;
    export const getCurrentUser = `/rest/user/current`;
    export const getUserById = (userId:number)=>`/rest/user/${userId}`;
    export const getFollowersFromUserByUserId = (userId:number) => `/rest/user/followers/${userId}`;
    export const getFollowingUsersFromUserByUserId = (userId:number) => `/rest/user/following/${userId}`;
}