// 🎌 WE'RE USING WEBPACK TO TRANSPILE AND ROLL UP MODULES 🎌
const path = require('path');

function resolve(dir) {
  return path.join(__dirname, './', dir);
}

module.exports = {
  entry: {
    // 🎌 OUR SOURCE FILE 🎌
    jsSource: './js/main.js',
  },
  output: {
    // 🎌 OUR DESTINATION 🎌
    filename: './js/bundle.js',
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules)/,
        loader: 'babel-loader',
      },
      {
        test: /\.json$/,
        use: 'json-loader',
      },
      {
        test: /\.vue$/,
        use: 'vue-loader',
      },
    ],
  },
  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      vue$: 'vue/dist/vue.esm.js',
      '@': resolve('js'),
    },
  },
};
