---
layout: docs
title: Getting Started
description: Installing and configuring the plugin
group: getting-started
toc: true
---

### Plugin Manager

The easiest way to install the Material Theme plugin is through IntelliJ's **plugin manager**. 
The plugin manager usually contains stable releases that made it through testing and feedback.

<img src="{{ site.img_folder | prepend: site.baseurl | replace: '//', '/' }}/installation/plugins.png">

Steps: 
1. Open Settings/Preferences (Windows/Linux: `Ctrl-S`, Mac: `Cmd-,`)
2. On the left, select **Plugins**
3. Click on the button `Browse repositories`
4. In the search field, type `Material Theme`
5. Double click on `Material Theme UI` to install it. 
6. Click the `OK` button and restart the IDE.

<img src="{{ site.img_folder | prepend: site.baseurl | replace: '//', '/' }}/installation/listplugin.png">

**Note**:
You can also install the [`Material Theme UI EAP`](https://github.com/mallowigi/material-theme-jetbrains-eap) instead. 
This is a version of the plugin that contains features in active development, but might contain bugs. 
If you're open to help beta-testing the plugin, download it and report any bugs you find.

### Manual Installation

If for some reason you don't want to use the plugin repository, or if you want to run your fork of the plugin, you can decide to install it manually.

Steps:
1. First you need to download the packed zip of the plugin. It is available in the GitHub repository: [Material Theme.zip](https://github.com/ChrisRM/material-theme-jetbrains/blob/master/Material%20Theme.zip).
2. Open Plugins settings (`Preferences -> Plugins`)
3. Instead of selecting `Browse repositories`, select **Install plugin from disk** and select the zip file.
4. Click OK and restart the IDE.

**Note**:

The packed zip file is **not** generated automatically. We usually release it in every release alongside the repository plugin, but it is not always in sync
with the plugin repository version. Therefore it's preferable to use the Plugin Manager.

### Support

The plugin is supported on all IDEs, but because of changes of the SDK specific versions are available only on latest builds:

- IDEs versions 2016.X and lower > up until version `0.2.3`
- IDEs versions 2017.1-2 > up until version `0.10.6`
- IDEs versions 2017.3+ > All versions
- Android Studio 2.2.3 > up until version `0.2.3`
- Android Studio 3.0.0+ > All versions
