var gulp = require('gulp'),
	jshint = require('gulp-jshint'),
	less = require('gulp-less'),
	concat = require('gulp-concat'),
	uglify = require('gulp-uglify'),
	cssmin = require('gulp-cssmin'),
	rename = require('gulp-rename'),
	del = require('del'),
	karma = require('karma').server,
	mocha = require('gulp-mocha');

// path variables
var app = {
	source: {
		scripts: ["public/app/**/*.js", "!public/app/tests/**/*.js"],
		styles: "public/stylesheets/**/*.css"
	},
	dest: {
		scripts: "public/dist/js",
		styles: "public/dist/css"
	}
};

var libs = {
	source: {
		scripts: ["public/libs/angular/angular.min.js",
					"public/libs/angular-bootstrap/ui-bootstrap.min.js",
					"public/libs/angular-bootstrap/ui-bootstrap-tpls.min.js",
					"public/libs/angular-ui-router/release/angular-ui-router.min.js",
					"public/libs/jquery/dist/jquery.min.js", "!public/libs/angular-mocks/angular-mocks.js"],
		styles: "public/libs/**/*.min.css",
		fonts: ["public/libs/bootstrap/fonts/**/*.*", "public/libs/font-awesome/fonts/**/*.*"]
	},
	dest: {
		scripts: "public/dist/js",
		styles: "public/dist/css",
		fonts: "public/dist/fonts"
	},
	bootstrap: {
    	main: 'public/libs/bootstrap/less/bootstrap.less',
    	dir:  'public/libs/bootstrap/less',
    	dest: 'public/stylesheets'
  	}
};

// Compile styles
gulp.task('styles', ['styles:app', 'styles:libs']);

gulp.task('styles:app', ['clean'], function() {
	return gulp.src(app.source.styles)
		.pipe(concat('app.css'))
		.pipe(gulp.dest(app.dest.styles))
		.pipe(cssmin())
		.pipe(rename({suffix: '.min'}))
		.pipe(gulp.dest(app.dest.styles));
});

gulp.task('styles:libs', ['clean'], function() {
	return gulp.src(libs.source.styles)
		.pipe(concat('base.min.css'))
		.pipe(gulp.dest(libs.dest.styles));
});

// compile script files
gulp.task('scripts', ['scripts:app', 'scripts:libs']);

gulp.task('scripts:app', ['clean'], function() {
	return gulp.src(app.source.scripts)
		.pipe(concat('app.js'))
		.pipe(gulp.dest(app.dest.scripts))
		.pipe(rename('app.min.js'))
		.pipe(uglify())
		.pipe(gulp.dest(app.dest.scripts));
});

gulp.task('scripts:libs', ['clean'], function() {
	return gulp.src(libs.source.scripts)
		.pipe(concat('base.min.js'))
		.pipe(uglify())
		.pipe(gulp.dest(libs.dest.scripts));
});

// fonts
gulp.task('fonts', ['clean'], function() {
	return gulp.src(libs.source.fonts)
		.pipe(gulp.dest(libs.dest.fonts));
});


// Lint Task
gulp.task('lint', function() {
	return gulp.src('public/app/*.js')
		.pipe(jshint())
		.pipe(jshint.reporter('default'));
});

// Clean task
gulp.task('clean', function() {
	del([app.dest.scripts + '/**/*',
		app.dest.styles + '/**/*',
		libs.dest.scripts + '/**/*',
		libs.dest.styles + '/**/*',
		libs.dest.fonts + '/**/*']);
});

// Testing task
gulp.task('test', ['test:client', 'test:server']) ;

gulp.task('test:client', function(done) {
	karma.start({
	  	configFile: __dirname + '/karma.config.js',
	  	singleRun: true
	}, done);
});

gulp.task('test:server', function() {
	gulp.src('test/**/*.js')
		.pipe(mocha({
			reporter: 'spec'
		}));
});

// watch task
gulp.task('watch', function() {
	gulp.watch([app.source.scripts, libs.source.scripts], ['lint', 'scripts']);
	gulp.watch([app.source.styles, libs.source.styles], ['styles']);
});


gulp.task('build', ['clean', 'lint', 'styles', 'scripts', 'fonts']);

// default task
gulp.task('default', ['build', 'watch']);


// Error handler
function handleError(err) {
  console.log(err.toString());
  this.emit('end');
}