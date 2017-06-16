# Material Theme UI (EAP)

This is a [Material Theme](https://github.com/equinusocio/material-theme) port of both the IDE and Color scheme for JetBrains products.

Originally this started as a fork of the [Material Theme UI plugin](https://github.com/ChrisRM/material-theme-jetbrains/issues/327) by [@ChrisRM](https://github.com/ChrisRM) but 
little by little I've been adding more and more features to look like even more like the Sublime plugin so I've moved it into its own project.

## !! UPDATE !!
The project has been merged into the main repository and I've got access to its contents as well. As a result I will work directly on that, but this repository will serve
as a EAP for publishing features before it hits the main repository :)

Buy me a beer: 
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=9D4HTMKUQAHZJ)

## Plugin
* [Support](#supported-ides)
* [Installation and Configuration](#installation)
* [Contribution](#contribution)
* [Screenshots](#screenshots)
* [Author](#author)

## Supported IDEs

Thanks to the awesome guys at [JetBrains](https://www.jetbrains.com/) the plugin is now supported on pretty much all IDEs from the same company. I've tested it on IntelliJ, PhpStorm, WebStorm, RubyMine, PyCharm, DataGrip and CLion.
For the rest of the IDEs I haven't tested it yet, but it should still work fine.

* Any JetBrains IDE (I haven't tested all of them, but I'm pretty certain it should work on the majority)
* Android Studio is not supported yet :^(

## Installation
1. [Open the Settings/Preferences dialog](https://www.jetbrains.com/idea/help/accessing-settings.html#openIdeSettings) (OSX/Unix: <kbd>⌘,</kbd>, Windows: <kbd>Ctrl+Alt+S</kbd>)
2. In the left-hand pane, select **Plugins**.
3. Click **Browse repositories...** and search for `Material Theme UI EAP`
4. Click **Install plugin** and confirm your intention to download and install the plugin.
5. Click **OK** in the **Settings** dialog and restart for the changes to take effect.
6. To switch the IDE theme (not the code color scheme), go to **Tools** -> **Material Theme** and choose one of the new themes.

Note: This is not installing a new Look And Feel, instead, it is **overwriting** the Darcula theme. Therefore, when you switch to another 
LAF and select back *Darcula*, you will revert to the original Darcula LAF. You will probably need to restart the IDE to retrieve the 
Material LAF.

### Set color scheme (code)
This plugin will not set the new color scheme for you, as that would cause a couple problems. You need to set the new color scheme manually:

1. Open the **Settings/Preferences** dialog again.
2. In the left-hand pane, select **Editor** -> **Colors & Fonts**.
3. In the **Scheme** dropdown, you'll find 4 new schemes: `Material Theme - Default`, `Material Theme - Darker`, `Material Theme - 
Palenight` and `Material Theme - Lighter`. 
4. Choose the scheme you like and hit **Apply** and **OK**.

Shortcut: <kbd>Ctrl+\`</kbd> (Windows: <kbd>Ctrl + ~</kbd>) then hit `1. Color scheme` and select your desired color scheme. 

**Important!**

This plugin is coming with these bundled color schemes, the same way that IntelliJ comes with predefined color schemes such as Darcula, Solarized, Monokai and such. 
Therefore whenever you change one of their values to suit your needs the IDE will be creating a copy of the bundled scheme, and it will appear **blued** in the select box. 

That also means that subsequent updates of the plugin's color schemes will __not__ be reflected to your copies. In order to receive them, you will need to reset your changes:

1. Go to **Colors and Fonts**. At the right of your selected color scheme there is a gear icon.
2. Click on the gear and choose *Restore defaults*.

Of course you will lose all your changes, so if you need to reflect them back after resetting, create a copy first and go change by change.

**Note:** Sometimes resetting the defaults will not work... In this case you will have to manually delete your copy:
https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs

Then delete the *"@_user_Material Theme"* icls files.

### Configuration

You can customize some plugin features in a Settings Panel under **Settings** -> **Tools** -> **Material Theme**:

Active Tab Settings:
- *Active Tab Highlight*: Customize active tab indicator color and thickness
- *Bold tabs*: Set tab font bold

Panel Settings:
- *Contrast Mode*: Add contrast to some of the IDE's components (currently only the Tabs and Tree)
- *Hide Icons*: Hide file icons (you must have Material Icons options on)
- *Compact Sidebar*: Reduce line height between list items in sidebars

Component Settings:
- *Custom Wallpaper*: Set a custom wallpaper for the "no files opened" page
- *Material Design components*: Enable/Disable Material Design components (Buttons, Progress Bars...)
- *Material Icons*: Enable/Disable the Material Theme icons to the original ones
- *Project View Decorators*: Enable/Disable the Project View Decorators such as "current opened folders" indicators

## Custom Wallpaper

*Custom Wallpaper* is not a feature made by the plugin but instead make use of one of the features of the IDE to set a background image. 
You can find it by opening the action panel (`Cmd-shift-a`) and type *Set background image*. The plugin option mainly make use of this feature unknown by many users.

Therefore some things need to be known:
- If you rather want to use the IDE's feature, turn off the option in the Material Theme completely. This will prevent the background to be changed at start.
- If you want to disable the background completely, set the custom wallpaper path to be empty and the checkbox on, then click Apply, and finally uncheck the option and save.
- And same if you want to disable the plugin, it will not revert back the blank wallpaper, you will have to open the IDE's dialog.

More features are to come!

## File Colors

*File Colors* is an option in IntelliJ IDEA to put a background color in the Project View and Tabs to certain files belonging to a scope. 
Such scopes may be Project Files, Non-Project Files, Libraries, Tests and so on. Originally, the IDE is bundled with two predefined File Colors:
`Non-Project Files` and `Tests`.

However, the colors prebundled are more suited to the original *Darcula* color scheme, so it is out of contrast with the bundled *Material Themes*.

So, much like color schemes, the *Material Theme* is now prebundled with specific file colors for `Non-project files`. You can see them in **Options -> Appearance -> File Colors*.

However, to be able to see them take priority, you would have to put your relevant file color **at the top**, before the ones bundled with IDEA. It is not done automatically as we don't want to override user settings, so make sure to do so if you want to use them.



## Development

### Requirements

* JDK 1.8

### Developing using IntelliJ Gradle

You can simplify development process thanks to Intellij's **Gradle plugin**. Install the plugin, restart the IDE and you will be prompted with a window
asking if you want to import the project as a Gradle project. After that IntelliJ will download the Gradle Wrapper and the tasks will appear in the Gradle Tool Panel on the right.

Import the project from the `build.gradle` file and develop as normal.  Make
sure you select JDK 8 in the import wizard.  The other defaults are fine.  You
can run the above mentioned CLI Gradle tasks directly in the "Gradle" Tool
Window, which expands from the right side of the screen.  To debug, find
"runIde" in the list, right-click it, and choose Run/Debug.

## Contribution

Pull requests are appreciated! I can use some help on bugs and features listed in [https://github.com/mallowigi/material-theme-jetbrains/projects/1], or you can send me some new ideas!

However, please note that this is a plugin dedicated to replicate the Sublime plugin, so it should coincide with its features and not deviate too much.

## Screenshots
#### Darker theme
![Darker theme](https://plugins.jetbrains.com/files/9377/screenshot_16889.png)

#### Default theme
![Default theme](https://plugins.jetbrains.com/files/9377/screenshot_16890.png)

#### Palenight theme
![Palenight theme](https://plugins.jetbrains.com/files/9377/screenshot_16892.png)

#### Lighter theme
![Lighter theme](https://plugins.jetbrains.com/files/9377/screenshot_16891.png)

#### Contrast Mode
![Choose theme](https://plugins.jetbrains.com/files/9377/screenshot_16888.png)


# Author and thanks
Author: [@Mallowigi](https://github.com/mallowigi)

Original author: [@ChrisRM](https://github.com/chrisrm)

**Thanks to [@equinusocio](https://github.com/equinusocio/material-theme) for the inspiration.**

**Icon Reference:**
- All file icons have been built using the tools provided by [A File Icon](https://github.com/ihodev/a-file-icon). Great thanks to them!
- And of course many thanks to the SVG creators:
* [**File-Icons**](https://github.com/file-icons/source/blob/master/charmap.md) 
* [**FontAwesome 4.7.0**](http://fontawesome.io/cheatsheet/)
* [**Mfizz**](https://github.com/file-icons/MFixx/blob/master/charmap.md)
* [**Devicons**](https://github.com/file-icons/DevOpicons/blob/master/charmap.md)
* [**Material Design Icons**](https://materialdesignicons.com/)

Also many thanks to other plugin developers for helping me solving A LOT of issues:
* [Nyan Progress Bar](https://plugins.jetbrains.com/plugin/8575-nyan-progress-bar)
* [Afterglow Theme](https://plugins.jetbrains.com/plugin/8066-afterglow-theme)
* [Git Toolbox](https://plugins.jetbrains.com/plugin/7499-gittoolbox)
* [Browse Word At Caret](https://plugins.jetbrains.com/plugin/201-browsewordatcaret)
* [GitIgnore](https://github.com/hsz/idea-gitignore)
* [Project Label](https://github.com/drinchev/project-label)
* And of course the original plugin [Material Theme UI](https://plugins.jetbrains.com/plugin/8006-material-theme-ui)

Thanks to all [original plugin contributors](https://github.com/mallowigi/material-theme-jetbrains/graphs/contributors), [this plugin contributors](https://github.com/mallowigi/material-theme-jetbrains/graphs/contributors)
and a special thanks to the guys at [JetBrains](https://www.jetbrains.com/) for contributing and showing interest in the project!
