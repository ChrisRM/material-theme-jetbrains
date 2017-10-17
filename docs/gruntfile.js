/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

module.exports = function (grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),

    imagemin: {
      main: {
        options: {
          optimizationLevel: 3,
          svgoPlugins: [{removeViewBox: false}],
        },
        files: [
          {
            // Set to true to enable the following options…
            expand: true,
            // cwd is 'current working directory'
            cwd: '',
            src: ['img/**/*.{png,jpg,svg}'],
            // Could also match cwd line above. i.e. project-directory/img/
            dest: 'assets/media/compressed/',
            flatten: true,
          },
        ],
      },
    },

    babel: {
      options: {
        sourceMap: true,
        presets: ['env'],
      },
      dist: {
        files: {
          'assets/js/app.js': 'js/app.js',
        },
      },
    },

    uglify: {
      global: {
        src: ['assets/js/*.js'],
        dest: 'assets/js/build/app.min.js',
      },
    },

    sass: {
      options: {
        outputStyle: 'compressed',
      },
      dist: {
        files: {
          'assets/css/main.css': 'sass/main.scss',
          'assets/css/grid.css': 'sass/grid.scss',
        },
      },
    },

    autoprefixer: {
      options: {
        browsers: ['> 1%'],
      },
      no_dest: {
        src: 'assets/css/*.css',
      },
    },

    copy: {
      css: {
        files: [
          {
            expand: true,
            src: ['assets/css/**'],
            dest: 'jekyllbuild/',
          },
        ],
      },
      js: {
        files: [
          {
            expand: true,
            src: ['assets/js/**'],
            dest: 'jekyllbuild/',
          },
        ],
      },
    },

    watch: {
      options: {
        livereload: true,
      },
      site: {
        files: ['{,*/}{,*/}{,*/}*.html',
          '{,*/}{,*/}{,*/}*.md',
          '{,*/}*.yml',
          '!jekyllbuild/{,*/}{,*/}*.*',
          '!node_modules/{,*/}*.*'],
        tasks: ['shell:jekyllBuild', 'copy'],
      },
      js: {
        files: ['js/{,*/}{,*/}*.js'],
        tasks: ['newer:babel', 'copy:js'],
      },
      css: {
        files: ['sass/{,*/}{,*/}{,*/}*.scss'],
        tasks: ['sass', 'autoprefixer', 'copy:css'],
      },
      images: {
        files: ['assets/img/{,*/}{,*/}*.{png,jpg,svg}'],
        tasks: ['newer:imagemin', 'shell:jekyllBuild', 'copy'],
      },
    },

    buildcontrol: {
      options: {
        dir: 'jekyllbuild',
        commit: true,
        push: true,
        message: 'Built jekyllbuild from commit %sourceCommit% on branch %sourceBranch%',
      },
      pages: {
        options: {
          remote: 'git@github.com:mallowigi/material-theme-jetbrains-eap.git',
          branch: 'master',
        },
      },
    },

    shell: {
      jekyllServe: {
        command: 'jekyll serve  --no-watch',
      },
      jekyllBuild: {
        command: 'jekyll build',
      },
    },
  });

  require('load-grunt-tasks')(grunt);

  grunt.registerTask('default', [
    'newer:imagemin',
    'sass',
    'autoprefixer',
    'newer:babel',
    'shell:jekyllBuild',
    'copy',
    'watch']);
  grunt.registerTask('build', [
    'imagemin',
    'sass',
    'autoprefixer',
    'babel',
    'uglify',
    'copy',
    'shell:jekyllBuild']);
  grunt.registerTask('deploy', [
    'buildcontrol:pages',
  ]);
};
