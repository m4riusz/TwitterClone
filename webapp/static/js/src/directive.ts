/**
 * Created by mariusz on 29.04.16.
 */


module TwitterClone.Directives {

    export class Directive {
        static getTweetDirective() {
            return {
                restrict: 'E',
                scope: {tweet: '=', ctrl: "="},
                templateUrl: 'view/tweetTemplate.html'
            }
        }

        static getUserDirective() {
            return {
                restrict: 'E',
                scope: {user: '=', ctrl: '='},
                templateUrl: 'view/userTemplate.html'
            }
        }
    }

}