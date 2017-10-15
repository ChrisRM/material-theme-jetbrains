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

module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),

    imagemin: {
      png: {
        options: {
          optimizationLevel: 7,
        },
        files: [
          {
            // Set to true to enable the following options…
            expand: true,
            // cwd is 'current working directory'
            cwd: '',
            src: ['docs/assets/img/*.png', 'docs/assets/img/**/*.png', 'docs/assets/img/**/**/*.png'],
            // Could also match cwd line above. i.e. project-directory/img/
            dest: 'docs/assets/media/compressed/',
            flatten: true,
            ext: '.png',
          },
        ],
      },
      jpg: {
        options: {
          progressive: true,
        },
        files: [
          {
            // Set to true to enable the following options…
            expand: true,
            // cwd is 'current working directory'
            cwd: '',
            src: ['docs/assets/img/*.jpg',
              'docs/assets/img/**/*.jpg',
              'docs/assets/img/**/**/*.jpg',
              'docs/assets/img/*.jpeg',
              'docs/assets/img/**/*.jpeg',
              'docs/assets/img/**/**/*.jpeg'],
            // Could also match cwd. i.e. project-directory/img/
            dest: 'docs/assets/media/compressed/',
            flatten: true,
            ext: '.jpg',
          },
        ],
      },
    },

    responsive_images: {
      square: {
        options: {
          sizes: [{
            width: 450,
            height: 450,
            aspectRatio: false,
          }],
        },
        files: [{
          expand: true,
          flatten: true,
          src: [
            'docs/assets/media/compressed/*.{jpg,gif,png}',
            'docs/assets/media/compressed/!crops/*.{jpg,gif,png}',
          ],
          cwd: '',
          dest: 'docs/assets/media/compressed/crops/450x450/',
        }],
      },
      thumbs: {
        options: {
          sizes: [{
            width: 450,
            height: 253,
            aspectRatio: false,
          }],
        },
        files: [{
          expand: true,
          flatten: true,
          src: [
            'docs/assets/media/compressed/*.{jpg,gif,png}',
            'docs/assets/media/compressed/!crops/*.{jpg,gif,png}',
          ],
          cwd: '',
          dest: 'docs/assets/media/compressed/crops/450x253/',
        }],
      },
    },

    babel: {
      options: {
        presets: ['env'],
      },
      dist: {
        files: {
          'docs/assets/js/app.js': 'js/app.js',
        },
      },
    },

    uglify: {
      global: {
        src: ['docs/assets/js/*.js'],
        dest: 'docs/assets/js/build/app.min.js',
      },
    },

    sass: {
      options: {
        outputStyle: 'compressed',
      },
      dist: {
        files: {
          'docs/assets/css/main.css': 'sass/main.scss',
          'docs/assets/css/grid.css': 'sass/grid.scss',
          'docs/assets/css/classic.css': 'sass/classic.scss',
          // you may want to remove these for your site
          // 'css/main_teal.css': 'sass/main_teal.scss',
        },
      },
    },

    autoprefixer: {
      options: {
        browsers: ['> 1%'],
      },
      no_dest: {
        src: 'docs/assets/css/*.css' // globbing is also possible here
      },
    },

    copy: {
      css: {
        files: [
          {
            expand: true,
            src: ['docs/assets/css/**'],
            dest: 'jekyllbuild/',
          },
        ],
      },
      js: {
        files: [
          {
            expand: true,
            src: ['docs/assets/js/**'],
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
        tasks: ['babel', 'copy:js'],
      },
      css: {
        files: ['sass/{,*/}{,*/}{,*/}*.scss'],
        tasks: ['sass', 'autoprefixer', 'copy:css'],
      },
      images: {
        files: ['docs/assets/img/{,*/}{,*/}*.{png,jpg}'],
        tasks: ['newer:imagemin', 'responsive_images', 'shell:jekyllBuild', 'copy'],
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
          remote: 'git@github.com:mallowigi/material-theme-jetbrains-eap.git', // change that
          branch: 'gh-pages' // adjust here
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

  grunt.registerTask('serve', ['shell:jekyllServe']);
  grunt.registerTask('default', ['newer:imagemin',
    'sass',
    'autoprefixer',
    'babel',
    'shell:jekyllBuild',
    'copy',
    'watch']);
  grunt.registerTask('build', [
    'sass',
    'autoprefixer',
    'babel',
    'uglify',
    'shell:jekyllBuild',
    'copy']);
  grunt.registerTask('deploy', ['buildcontrol:pages']);
};
