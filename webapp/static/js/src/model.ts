/**
 * Created by mariusz on 13.04.16.
 */

module TwitterClone.Models {

    export class User {
        public Id:number;
        public Username:string;
        public Email:string;
        public Role:string;
        public Date:Date;
        public Enabled:boolean;
        public Followers:User[];
        public Following:User[];
    }

    export class Tweet {
        public Id:number;
        public Owner:User;
        public Content:string;
        public Comments:Tweet[];
        public Date:Date;
    }

}