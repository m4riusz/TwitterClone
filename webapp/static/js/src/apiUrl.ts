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

    export const getTweets = `/rest/user/tweet`;
    export const getTweetsFromUser = (userId:number) => `/rest/user/${userId}/tweet`;
    export const getTweetsFromFollowingUsers = (userId:number) => `/rest/user/tweet/following/${userId}`;
    export const getTweetById = (tweetId:number) => `/rest/user/tweet/${tweetId}`;
    export const getCommentsFromTweet = (tweetId:number) => `/rest/user/tweet/${tweetId}/comment`;
    export const createTweet = `/rest/user/tweet`;
    export const deleteTweet = `/rest/user/tweet`;
    export const createTweetComment = (tweetId:number)=> `/rest/user/tweet/${tweetId}/comment`;

}