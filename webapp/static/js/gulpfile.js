var gulp = require("gulp");
var watch = require("gulp-watch");
var tsc = require('gulp-typescript');
var clean = require('gulp-clean');

var tsFiles = [".typings/main/**/*.ts", "src/**/*.ts"];
var filesToClean = ["src/**/*.js","src/**/*.map"];

var compilation = {
    target: 'es6',
    module: 'commonjs',
    declaration: false,
    "sourceMap": true,
    "noImplicitAny": false,
    "removeComments": false,
    "preserveConstEnums": false
};

gulp.task("build", function () {
    return gulp.src(tsFiles)
        .pipe(tsc(compilation))
        .pipe(gulp.dest(function (file) {
            return file.base;
        }))
});

gulp.task("clean", function () {
    return gulp.src(filesToClean, {read: false})
        .pipe(clean());
});

gulp.task("default", ["build"]);

gulp.task("watch", function () {
    gulp.watch(tsFiles, ["build"]);
});