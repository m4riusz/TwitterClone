/**
 * Created by mariusz on 13.04.16.
 */

module TwitterClone.Urls {

    export const getAllUsers = '/rest/user';
    export const getCurrentUser = '/rest/user';
    export const getUserById = (userId:number)=>`/rest/user/${userId}`;
}