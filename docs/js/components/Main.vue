<template>
  <div class="main">
    <div class="featimg theme-img"></div>
  </div>
</template>

<script>
  document.getElementsByTagName('body')[0].classList.add('oceanic');
  export default {
    name: 'main',
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
