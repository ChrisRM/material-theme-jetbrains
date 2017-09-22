<template>
  <div class="main">
    <div class="theme-chooser">
      <a href="void:javascript(0)" class="theme"
         v-for="theme in themes"
         v-on:click="setTheme(theme)"
         v-bind:class="theme.className"
         v-bind:title="theme.name"></a>
    </div>
    <div class="featimg theme-img"></div>
  </div>
</template>

<script>
  export default {
    name: 'main',
    methods: {
      setBodyClass(css) {
        const classList = document.getElementsByTagName('body')[0].classList;
        classList.remove('oceanic', 'darker', 'lighter', 'palenight');
        classList.add('index', css);
      },
      setTheme(theme) {
        this.setBodyClass(theme.className);
      },
    },

    data() {
      return {
        themes: [
          {name: 'Default', className: 'oceanic'},
          {name: 'Darker', className: 'darker'},
          {name: 'Lighter', className: 'lighter'},
          {name: 'Palenight', className: 'palenight'},
        ],
      };
    },
    mounted() {
      this.setBodyClass('oceanic');
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .theme-chooser {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    margin: 1em 0;
  }

  .theme {
    width: 24px;
    height: 24px;
    display: inline-block;
    padding: 0;
    border-radius: 50%;
    box-shadow: 0 8px 11px 0 rgba(0, 0, 0, .23), 0 -1px 10px 0 rgba(0, 0, 0, .1)
  }

  .theme.oceanic {
    background: #263238;
  }

  .theme.darker {
    background: #212121;
  }

  .theme.palenight {
    background: #292D3E;
  }

  .theme.lighter {
    background: #fafafa;
  }

</style>
