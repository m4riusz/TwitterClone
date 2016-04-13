var gulp = require("gulp");
var watch = require("gulp-watch");
var tsc = require('gulp-typescript');

var tsFiles = [".typings/main/**/*.ts", "src/**/*.ts"];

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

gulp.task("default", ["build"]);

gulp.task("compile", ["build"]);

gulp.task("watch", function () {
    gulp.watch(tsFiles, ["compile"]);
});