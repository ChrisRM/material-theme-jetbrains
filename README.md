<div align="center">
<a href="http://mallowigi.github.io/material-theme-jetbrains-eap">
<img src="logo.svg" width="320" height="320" alt="logo"></img>
</a>
</div>

# Material Theme UI for Jetbrains

This is a [Material Theme](https://github.com/equinusocio/material-theme) port of both the IDE and Color scheme for JetBrains products.

Plugin page:
https://plugins.jetbrains.com/plugin/8006-material-theme-ui

Documentation (in progress):
http://mallowigi.github.io/material-theme-jetbrains-eap

![laptop-full](laptop-full.png)

Buy me a beer: 
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=LSF7K29JBPMWU&lc=US&item_name=Material%20Theme%20JetBrains%20Development&item_number=m1&currency_code=NOK&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted)

And him too :*
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.me/mallowigi)

<script type='text/javascript' src='https://ko-fi.com/widgets/widget_2.js'></script><script type='text/javascript'>kofiwidget2.init('Buy Me a Coffee', '#46b798', 'U7U27VXE');kofiwidget2.draw();</script>

## Plugin
* [Support](#supported-ides)
* [Installation and Configuration](#installation)
	* [Switch Theme](#switching-the-ide-theme)
	* [Custom Themes](#custom-themes)
	* [Switch Color Scheme](#set-color-scheme-code)
	* [File Colors](#file-colors)
	* [Default Font](#default-font)
	* [Contrast Mode](#contrast-mode)
	* [Custom Accents](#custom-accents)
* [Configuration](#configuration)
* [Development](#development)
* [Contribution](#contribution)
* [Known Issues](#known-issues)
* [Authors](#authors)
* [Icon Reference](#icon-reference)
* [Color Palette](#color-palette)

## Supported IDEs

Thanks to the awesome guys at [JetBrains](https://www.jetbrains.com/) the plugin is now supported on pretty much all IDE, however there might be some issues on Rider since it's a different SDK. Android Studio is partly supported.

* IDEs versions 2016.X and lower > up until version 0.2.3
* IDEs versions 2017.1-2 > up until version 0.10.6
* IDEs versions 2017.3 > All versions
* Android Studio 2.2.3 > up until version 0.2.3
* Android Studio 3.0.0+ > All versions

## Installation
1. [Open the Settings/Preferences dialog](https://www.jetbrains.com/idea/help/accessing-settings.html#openIdeSettings) (OSX/Unix: <kbd>⌘,</kbd>, Windows: <kbd>Ctrl+Alt+S</kbd>)
2. In the left-hand pane, select **Plugins**.
3. Click **Browse repositories...** and search for `Material Theme UI`
4. Click **Install plugin** and confirm your intention to download and install the plugin.
5. Click **OK** in the **Settings** dialog and restart for the changes to take effect.

Note: This is not installing a new Look And Feel, instead, it is **overwriting** the Darcula theme. Therefore, when you switch to another LAF and select back *Darcula*, you will revert to the original Darcula LAF. You will probably need to restart the IDE to retrieve the Material LAF.

--------------------

### Switching the IDE theme

There are many ways to change the Material Theme Look and Feel of the IDE:
- From the IDE menu: `Tools -> Material Theme`
- From the main toolbar, there is an icon like a blue dot.
- From the `Search Everything`, look for `Material`
- From the `Quick Switch` panel <kbd>Ctrl+\`</kbd> (Windows: <kbd>Ctrl + ~</kbd>)

There are currently 4 bundled themes:
- **Oceanic Theme** - A nice Oceanic Blue like theme
- **Darker Theme** - A more classic Dark Theme
- **Lighter Theme** - A light variation of the theme
- **Palenight Theme** - A more purplish theme

And you can also disable the theming and revert to the original Darcula, while still keeping Material Theme additions such as the icons and UI components.

-------------------

### Custom Themes

Since version 0.10.0 with the possibility to add custom themes, three new themes have been added (without their color schemes and file colors):
- **Monokai Pro**, the theme used in **Sublime Text**
- **Arc Dark**, from https://github.com/horst3180/arc-theme
- **One Dark**, from **Atom One Dark Theme**

With the ability to set up custom themes, we can even start to host a gallery of custom themes I will add some that are the most popular, or even give the ability to import them. But it's still far away! :)

-----------------

### Set color scheme (code)
This plugin will not set the new color scheme for you, as that would cause a couple problems. You need to set the new color scheme manually:

1. Open the **Settings/Preferences** dialog again.
2. In the left-hand pane, select **Editor** -> **Colors & Fonts**.
3. In the **Scheme** dropdown, you'll find 4 new schemes: `Material Default`, `Material Darker`, `Material Palenight` and `Material Lighter`.
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

-------------

### Custom Themes

Since version 0.10.0 it is now possible to customize the Material Theme's theme colors to your own desires. You can find the options under **Appearance** -> **Material Custom Theme**. There you can find the different colors used and customize them to whatever suits your needs. Let your creative desires bloom!

In order to select your custom theme, you will need to select the theme inside the *Material Theme Chooser*, called *Custom Theme*.

A light variant is also available.

-----------------

### Material Status File Colors

Since the latest releases Jetbrains removed the *File Status colors* from the Color Schemes and set it inside **Version Control** -> **File Status Colors**. However doing so removed the customability of the file status colors from the custom color schemes. This feature brings it back.

You can find the settings to set the file colors under **Colors and Fonts** -> **Material File Colors**. Of course, this is *per color scheme*, not *per theme*, so please take this into consideration.

**Note**: This feature conflicts with the **File Status Colors** IDE feature. Therefore it is highly preferrable to use the **Material File Colors** for all file colors based customizations. If you prefer to use IDE's settings instead, be aware that bugs could occur that I didn't take into consideration.

**Note 2**: This feature is modifying the IDE, meaning that removing/disabling the feature will keep these colors in place even after restarting the IDE. For a complete cleanup, you will need to delete the **@_user_Darcula.icls** file from inside the colors settings directory.

https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs

Then delete the *"@_user_Darcula"* icls files.

-----------------

### File Colors

*File Colors* is an option in IntelliJ IDEA to put a background color in the Project View and Tabs to certain files belonging to a scope. 
Such scopes may be Project Files, Non-Project Files, Libraries, Tests and so on. Originally, the IDE is bundled with two predefined File Colors: `Non-Project Files` and `Tests`.

However, the colors prebundled are more suited to the original *Darcula* color scheme, so it is out of contrast with the bundled *Material Themes*.

So, much like color schemes, the *Material Theme* is now prebundled with specific file colors for `Non-project files`. You can see them in **Options -> Appearance -> File Colors* (You need to install them first through the `Install Material File Colors` Action from the `Panel Settings` menu.

However, to be able to see them take priority, you would have to put your relevant file color **at the top**, before the ones bundled with IDEA. 
It is not done automatically as we don't want to override user settings, so make sure to do so if you want to use them.

-----------------

### Default Font

The plugin is programmed to replace the main IDE font with the one usually used in Material Design apps and websites, `Roboto`. Of course, you can still replace it with a custom font of yours in the settings: *Appearance -> Override defaults fonts*.

Don't forget though that the plugin doesn't come with the aforementioned font. Therefore, you need to install it first to be able to see the results: https://fonts.google.com/specimen/Roboto?selection.family=Roboto 

Just install the font (if you want to), then restart the IDE to see if you like it :)

-------------------

### Contrast Mode

Much like the Sublime plugin, the plugin also comes with a **Contrast Mode** to put more contrast between different components of the IDE. These elements are:
- Sidebars: Whether it is the Project View Sidebar, the Settings Sidebar or in the Version Control Tool Window.
- Tabs: Contrast between the active tab and the other tabs.
- Tables, Lists and Inputs

-------------------

### Custom Accents

From version 0.7.0 the plugin can now have different accent colors! To change the Accent Color, just press on the *Accents* Action Button on the Toolbar/Quick Switch/Tools,
then select the desired accent color.

The Accent Color is changing:
- Active Tab Highlight Color (unless the custom tab highlight color is checked)
- Open folder icon
- Tool window icons hover color
- Modified file color
- Sliders
- Branches in VCS Log
- Progress Bar, Active Input, Selected checkbox, Selected Radio Button
- Edited and Pinned file indicators
- Fold/Unfold icons
- Documentation links and bold texts
- Pressed Action Button Toggle state
- Autocomplete
- Quick info
- Scrollbars (when the accent scrollbars is checked)

---------------------

### Project View Decorators

Another feature taken from the Sublime Plugin is the ability to differentiate **opened directories** from closed ones. *"Opened directories"* actually mean directories from the project view whose one of their children is opened in the Editor.

The plugin is decorating these directories by setting an "outlined" directory icon tinted with the selected *Accent color*.

You can disable this behavior through the Settings or the Action Buttons in the toolbar.

--------------------

## Configuration

You can customize some plugin features in a Settings Panel under **Settings** -> **Appearance** -> **Material Theme**:

Active Tab Settings:
- *Active Tab Highlight*: Customize active tab indicator color and thickness (works also for the Project View selected row)
- *Tabs Height*: Set a custom height for tabs (between 25 and 60 pixels)
- *Uppercase Bold Tabs*: Set the Editor Tabs in Bold Upper Case 

Panel Settings:
- *Contrast Mode*: Add contrast to some of the IDE's components (currently only the Tabs and Tree)
- *Hide Icons*: Hide file icons (you must have Material Icons options on)
- *Custom Sidebar Height*: Set custom line height in Project View (min: 18, max: 30)
- *Custom Tree Indent*: Increase or reduce indentation in the sidebar (min: 2, max: 8)
- *Bold directories*: Set bold font weight for directories in the Project View
- *Compact Status Bar*: Reduce the height of the status bar (this is the default height)
- *Compact Table Cells*: Reduce the height of table headers and cells
- *Custom Accent Color*: Set a custom color for accent color
- *Arrows Style*: Change the style of the arrows in trees

Component Settings:
- *Custom Wallpaper*: Set a custom wallpaper for the "no files opened" page
- *Material Design components*: Enable/Disable Material Design components (Buttons, Progress Bars...)
- *Material Icons*: Enable/Disable the Material Theme icons to the original ones
- *Material Theme*: Enable/Disable the material theme styles
- *Material Fonts*: Change the default font to Roboto and increases the Project View font
- *Theme in Status Bar*: Add an indicator of the current selected UI Theme in the status bar
- *Project View Decorators*: Enable/Disable the Project View Decorators. See the above section about separators.
- *Transparent Scrollbars*: Enable/Disable transparency in scrollbars
- *Accent Scrollbars*: Set scrollbar color the same color as accent color
- *Dark Title Bar*: Activate the coloring of the application title bar in Mac OSX/Windows

Keep in touch for new features!

---------------

## Development

### Requirements

* JDK 1.8

### Developing using IntelliJ Gradle

You can simplify development process thanks to Intellij's **Gradle plugin**. Install the plugin, restart the IDE and you will be prompted with a window asking if you want to import the project as a Gradle project. After that IntelliJ will download the Gradle Wrapper and the tasks will appear in the Gradle Tool Panel on the right.

Import the project from the `build.gradle` file and develop as normal.  Make sure you select JDK 8 in the import wizard.  The other defaults are fine.  You can run the above mentioned CLI Gradle tasks directly in the "Gradle" Tool Window, which expands from the right side of the screen.  To debug, find "runIde" in the list, right-click it, and choose Run/Debug.

---------------

## Contribution

Pull requests are appreciated! I can use some help on bugs and features listed in 
[https://github.com/ChrisRM/material-theme-jetbrains/issues], or you can send me some new ideas!

However, please note that this is a plugin dedicated to replicate the Sublime plugin, so it should coincide with its features and not deviate too much.

----------------

## Known Issues

### Color Schemes copy

As stated in the Color Schemes section, whenever you change an attribute of the default color schemes a copy will be
created, and from then on you won't be able to receive updates to the color schemes. However, in some occasions, especially when using
third party plugins, the color schemes will be altered without your input, resulting in the aforementioned effect.

For instance, the _Markdown Navigator_ plugin creates a lot of properties when selecting a scheme, and even selecting `Restore Defaults` will still result to creating a copy.


# Authors:
 
Twitter: [@crmag](https://twitter.com/crmag)
[@ChrisRM](https://github.com/chrisrm)
[@Mallowigi](https://github.com/mallowigi)

**Thanks to [@equinusocio](https://github.com/equinusocio/material-theme) for the inspiration.**

# YourKit

Performance issues are troubleshooted using:

[![YourKit](https://www.yourkit.com/images/yklogo.png)](https://www.yourkit.com/java/profiler/)

YourKit supports open source projects with its full-featured Java Profiler. 
YourKit, LLC is the creator of <a href="https://www.yourkit.com/java/profiler/">YourKit Java Profiler</a> 
and <a href="https://www.yourkit.com/.net/profiler/">YourKit .NET Profiler</a>, 
innovative and intelligent tools for profiling Java and .NET applications. 

## Icon Reference

- All file icons have been built using the tools provided by [A File Icon](https://github.com/ihodev/a-file-icon). Great thanks to them!
- And of course many thanks to the SVG creators:
* [**File-Icons**](https://github.com/file-icons/source/blob/master/charmap.md) 
* [**FontAwesome 4.7.0**](http://fontawesome.io/cheatsheet/)
* [**Mfizz**](https://github.com/file-icons/MFixx/blob/master/charmap.md)
* [**Devicons**](https://github.com/file-icons/DevOpicons/blob/master/charmap.md)
* [**Octicons**](https://octicons.github.com)
* [**Material Design Icons**](https://materialdesignicons.com/)
* [**Many FlatIcons authors**](https://www.flaticon.com/)

Thanks also for [@yonnyz](https://twitter.com/yonnyz) for the theme icons!

Also many thanks to other plugin developers for helping me solving A LOT of issues:
* [Nyan Progress Bar](https://plugins.jetbrains.com/plugin/8575-nyan-progress-bar)
* [Afterglow Theme](https://plugins.jetbrains.com/plugin/8066-afterglow-theme)
* [Git Toolbox](https://plugins.jetbrains.com/plugin/7499-gittoolbox)
* [Browse Word At Caret](https://plugins.jetbrains.com/plugin/201-browsewordatcaret)
* [GitIgnore](https://github.com/hsz/idea-gitignore)
* [Project Label](https://github.com/drinchev/project-label)

Thanks to all [original plugin contributors](https://github.com/ChrisRM/material-theme-jetbrains/graphs/contributors), [EAP contributors](https://github.com/mallowigi/material-theme-jetbrains-eap/graphs/contributors)
and a special thanks to the guys at [JetBrains](https://www.jetbrains.com/) for contributing and showing interest in the project!

## Color Palette

| Color     | Default / Darker | Lighter   |
|:----------|:-----------------|:----------|
| Red       | `#FF5370`        | `#E53935` |
| Pink      | `#f07178`        | `#FF5370` |
| Orange    | `#F78C6C`        | `#F76D47` |
| Yellow    | `#FFCB6B`        | `#FFB62C` |
| Green     | `#C3E88D`        | `#91B859` |
| Pale Blue | `#B2CCD6`        | `#8796B0` |
| Cyan      | `#89DDFF`        | `#39ADB5` |
| Blue      | `#82AAFF`        | `#6182B8` |
| Purple    | `#C792EA`        | `#7C4DFF` |
| Violet    | `#bb80b3`        | `#945EB8` |
| Brown     | `#ab7967`        | `#ab7967` |


**Color Theme Reference**

| Color                | Default  | Darker   | Lighter  | Palenight|
| -------------------- | -------- | -------- | -------- | -------- |
| Background           | `263238` | `212121` | `FAFAFA` | `292D3E` |
| Foreground           | `B0BEC5` | `B0BEC5` | `A7ADB0` | `B0BEC5` |
| Selection            | `546E7A` | `424242` | `546E7A` | `676E95` |
| Primary Color        | `607D8B` | `616161` | `A7ADB0` | `A6ACCD` |
| Alternative Color    | `546E7A` | `616161` | `B0BEC5` | `676E95` |
| Inactive             | `415967` | `474747` | `D2D4D5` | `4E5579` |

**Accent Colors**

| Accent       | Color     |
|:-------------|:----------|
| Turquoise    | `80CBC4`  |
| Acid Lime    | `c6ff00`  |
| Amethyst     | `ab47bc`  |
| Aquamarine   | `64ffda`  |
| Breaking Bad | `388e3c`  |
| Brick        | `e57373`  |
| Coffee       | `795548`  |
| Cyan         | `00bcd4`  |
| Daisy        | `FFEB3B`  |
| Dodger Blue  | `2979ff`  |
| Fuschia      | `E91E63`  |
| Gold         | `FFD700`  |
| Graphite     | `616161`  |
| Indigo       | `3F51B5`  |
| Lime         | `7CB342`  |
| Orange       | `ff7042`  |
| Pomegrenate  | `f44336`  |
| Sky          | `84ffff`  |
| Slate        | `607D8B`  |
| Strawberry   | `ff4081`  |
| Teal         | `009688`  |
| Tomato       | `F44336`  |

