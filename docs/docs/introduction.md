---
layout: docs
title: Introduction
description: What is this plugin?
group: introduction
toc: true
---

#### Material Theme

**Material Theme** is a *plugin* for Jetbrains IDEs providing a
[Material Design](https://material.io/guidelines/)
experience for Jetbrains products.

It is based on the [Material Theme for Sublime](https://github.com/equinusocio/material-theme) plugin while trying to bring its own lot of features.

<div class="featimg theme-img" style="padding: 2rem"></div>

#### Features

##### A new look and feel(s)

<img class="promo-img" src="{{ site.img_folder | prepend: site.baseurl | replace: '//', '/' }}/lookandfeel.svg">

This plugins installs fresh, modern and beautiful Look and Feels for the Intellij platform.
Up to this day, the Jetbrains engineers did not provide an API for creating custom Look and Feels, leaving us
stuck with the prebundled *Darcula* and *IntelliJ*. Nonetheless, this plugin is exploiting the Java Swing theming capabilities
to override those setup by Darcula in order to provide a new Look And Feel based on the [Sublime Material Theme](https://github.com/equinusocio/material-theme) by Mattia Astorino.

##### Colorful Color Schemes

<img class="promo-img" src="{{ site.img_folder | prepend: site.baseurl | replace: '//', '/' }}/colorschemes.svg">

Along with the Look and Feels, the plugin also comes with prebundled color schemes taken from the [Sublime Material Theme](https://github.com/equinusocio/material-theme).
Each one of the color schemes is adapted to the Theme of the same name, giving the impression of a slick, border-free interface. Besides, the color schemes have been worked from the
ground up to support a multitude of languages, from the most popular to the most esoteric out there (looking at you, Elm!)

##### Beautiful icons

<img class="promo-img" src="{{ site.img_folder | prepend: site.baseurl | replace: '//', '/' }}/icons-folder.svg">


The plugin also replaces all (almost) all IDE icons with [**Material Design Icons**](https://materialdesignicons.com/) in order to provide a seamless experience with
other Material Design products. Moreover, the plugin is also replacing file icons according to their extension/framework/library for a better visual grepping, according
to the principles from [Atom File Icons](https://atom.io/packages/file-icons).

##### Material Design Components

<img class="promo-img" src="{{ site.img_folder | prepend: site.baseurl | replace: '//', '/' }}/background.svg">


Aside from the Look and Feels, Colors and Icons, the plugin is also the first to completely customize the appearance of the IDE
by replacing the original components with [Material Design Components](https://material.io/components/) such as: *buttons*, *text fields*, *checkboxes*, *tabs* and so on.

##### Customizable

<img class="promo-img" src="{{ site.img_folder | prepend: site.baseurl | replace: '//', '/' }}/customizable.svg">


Providing such a large set of features and customizations does not go without options to enable or disable particular features.
The plugin comes with a large panel of settings for customizing almost everything, ranging from the _tab height_ to the _scrollbars transparency_,
including _accent color_, _sidebar row height_ or _custom wallpaper_. There even exists the possibility to create
your own theme, the only limit being your imagination!

##### Easily extensible

<img class="promo-img" src="{{ site.img_folder | prepend: site.baseurl | replace: '//', '/' }}/extensible.svg">


Besides all these wonderful features, the plugin is also easy to extend and modify for your own purposes.
Since we cannot satisfy everyone's requests, giving the ability for everyone to tweak the plugin to their needs
is the best course of action. Themes, Colors, Icons... each of these are pretty easy to tweak.


#### The Creators

##### Mattia Astorino ([@equinusocio](https://github.com/equinusocio))

<img class="avatar-img" src="https://avatars1.githubusercontent.com/u/10454741?v=4&s=460">

The original theme creator. Web developer and designer based in Italy, he is the one that submitted the idea of the Material Theme to spruce up development 
with the Sublime Text editor. The project quickly gained traction and got featured in the GitHub showcase very quickly, and other people caught interest of 
porting the theme to other editors and applications as well, such as Atom, iTerm, Slack or Vim. He is currently developing the [Visual Studio Code](https://github.com/equinusocio/vsc-material-theme)
version of the theme.

##### Chris Magnussen ([@ChrisRM](https://github.com/chrisrm))

<img class="avatar-img" src="https://avatars3.githubusercontent.com/u/309292?v=4&s=460">

Web developer and designer based in Norway, he is the original creator of the Jetbrains plugin. Having discovered the way to customize the look and feel of 
the Jetbrains IDEs, he experimented on porting the already successful Material Theme on it, gaining immediately fame even across Jetbrains employees, who 
contributed to the project in its beginning. The project got famous very quickly, being referenced on Laracasts, blogs and tutorial videos. 

##### Elior Boukhobza ([@mallowigi](https://github.com/mallowigi))

<img class="avatar-img" src="https://avatars1.githubusercontent.com/u/5015756?v=4&s=460">

Software engineer and developer based in Israel, he is the one who took the reins of the project after being left for a long time with no successor in sight.
He took where Chris left off and expanded the already successful Material Theme by customizing it even more, adding customization features, changing the appearance
of the components and even giving the ability to set custom themes. He is currently in charge on the development of the project alongside ChrisRM.
