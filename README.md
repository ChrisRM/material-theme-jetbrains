# Material Theme UI for Jetbrains 

This is a [Material Theme](https://github.com/equinusocio/material-theme) port of both the IDE and Color scheme for JetBrains products.

Special thanks to [@mallowigi](https://github.com/mallowigi) for doing a fantastic job keeping the plugin alive and extending it with some very nice features. Well done!

Note: You can have a sneak peek of the features to come in his repository:
[Material Theme Jetbrains EAP](https://github.com/mallowigi/material-theme-jetbrains-eap)

Buy me a beer: 
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=LSF7K29JBPMWU&lc=US&item_name=Material%20Theme%20JetBrains%20Development&item_number=m1&currency_code=NOK&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted)

And him too :*
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=9D4HTMKUQAHZJ)
[![Flattr this git repo](http://api.flattr.com/button/flattr-badge-large.png)](https://flattr.com/submit/auto?user_id=Mallowigi&url=https://github.com/ChrisRM/material-theme-jetbrains&title=material-theme-jetbrains&language=&tags=github&category=software) 

## Plugin
* [Support](#supported-ides)
* [Installation and Configuration](#installation)
	* [Switch Theme](#switching-the-ide-theme)
	* [Custom Themes](#custom-themes)
	* [Switch Color Scheme](#set-color-theme-code)
	* [File Colors](#file-colors)
	* [Default Font](#default-font)
	* [Contrast Mode](#contrast-mode)
	* [Custom Wallpaper](#custom-wallpaper)
	* [Custom Accents](#custom-accents)
* [Configuration](#configuration)
* [Development](#development)
* [Contribution](#contribution)
* [Known Issues](#known-issues)
* [Screenshots](#screenshots)
* [Authors](#authors)
* [Icon Reference](#icon-reference)
* [Color Palette](#color-palette)

## Supported IDEs

Thanks to the awesome guys at [JetBrains](https://www.jetbrains.com/) the plugin is now supported on pretty much all IDE, however there 
might be some issues on Rider since it's a different SDK. Android Studio is partly supported.

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

Note: This is not installing a new Look And Feel, instead, it is **overwriting** the Darcula theme. Therefore, when you switch to another 
LAF and select back *Darcula*, you will revert to the original Darcula LAF. You will probably need to restart the IDE to retrieve the 
Material LAF.

--------------------

### Switching the IDE theme

There are many ways to change the Material Theme Look and Feel of the IDE:
- From the IDE menu: `Tools -> Material Theme`
- From the main toolbar, there is an icon like a blue dot.
- From the `Search Everything`, look for `Material`
- From the `Quick Switch` panel <kbd>Ctrl+\`</kbd> (Windows: <kbd>Ctrl + ~</kbd>)

There are currently 4 bundled themes:
- **Default Theme** (also called Oceanic Theme) - A nice Oceanic Blue like theme
- **Darker Theme** - A more classic Dark Theme
- **Lighter Theme** - A light variation of the theme
- **Palenight Theme** - A more purplish theme

And you can also disable the theming and revert to the original Darcula, while still keeping Material Theme additions such as the icons and UI components.

-------------------

### Custom Themes

Since version 0.10.0 with the possibility to add custom themes, three new themes have been added (without their color schemes and file 
colors): 
- **Monokai**, the theme used in **Sublime Text**
- **Arc Dark**, from https://github.com/horst3180/arc-theme
- **One Dark**, from **Atom One Dark Theme**

With the ability to set up custom themes, we can even start to host a gallery of custom themes I will add some that are the most popular,
 or even give the ability to import them. But it's still far away! :)

-----------------

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

-------------

### Custom Themes (alpha version)

Since version 10.0 it is now possible to customize the Material Theme's theme colors to your own desires. You can find the options under 
**Appearance** -> **Material Custom Theme**. There you can find the different colors used and customize them to whatever suits your needs
. Let your creative desires bloom!

In order to select your custom theme, you will need to select the theme inside the *Material Theme Chooser*, called *Custom Theme*.

**Note (Important!)**: Because of how things work, currently it is not possible to change the whole IDE colors on the fly. You will need 
to do a full restart of the IDE to get your custom colors to work on the whole application.

-----------------

### Material Status File Colors

Since the latest releases IntelliJ IDEA removed the option to set colors to specific file statuses (added to VCS, modified, ignores...). 
This plugin is giving the opportunity to set it back, but only in the Project Tree.

You can find the settings to set the file colors under **Colors and Fonts** -> **Material File Colors**. Of course, this is *per color 
scheme*, not *per theme*, so please take this into consideration. 

**Note**: Jetbrains added back the File Colors under **Version Control** > **File Status Colors**. This is something rather new from 2017
.3 so for now the plugin ignores these settings and uses the *Material File Colors* settings instead, but it could change in the future.

-----------------

### File Colors

*File Colors* is an option in IntelliJ IDEA to put a background color in the Project View and Tabs to certain files belonging to a scope. 
Such scopes may be Project Files, Non-Project Files, Libraries, Tests and so on. Originally, the IDE is bundled with two predefined File Colors:
`Non-Project Files` and `Tests`.

However, the colors prebundled are more suited to the original *Darcula* color scheme, so it is out of contrast with the bundled *Material Themes*.

So, much like color schemes, the *Material Theme* is now prebundled with specific file colors for `Non-project files`. You can see them in **Options -> Appearance -> File Colors*.

However, to be able to see them take priority, you would have to put your relevant file color **at the top**, before the ones bundled with IDEA. 
It is not done automatically as we don't want to override user settings, so make sure to do so if you want to use them.

-----------------

### Default Font

The plugin is programmed to replace the main IDE font with the one usually used in Material Design apps and websites, `Roboto`. Of course, you can still replace it with a custom font of yours in the settings:
*Appearance -> Override defaults fonts*.

Don't forget though that the plugin doesn't come with the aforementioned font. Therefore, you need to install it first to be able to see the results: https://fonts.google.com/specimen/Roboto?selection.family=Roboto 

Just install the font (if you want to), then restart the IDE to see if you like it :)

-------------------

### Contrast Mode

Much like the Sublime plugin, the plugin also comes with a **Contrast Mode** to put more contrast between different components of the IDE. These elements are:
- Sidebars: Whether it is the Project View Sidebar, the Settings Sidebar or in the Version Control Tool Window.
- Tabs: Contrast between the active tab and the other tabs.
- Tables, Lists and Inputs

To set/unset the Contrast Mode, either go to *Settings -> Tools -> Material Theme* and check/uncheck `Contrast Mode`, or click on the black and white icon on the Main Toolbar.

-------------------

### Custom Wallpaper

*Custom Wallpaper* is not a feature made by the plugin but instead make use of one of the features of the IDE to set a background image. 
You can find it by opening the action panel (`Cmd-shift-a`) and type *Set background image*. The plugin option mainly make use of this feature unknown by many users.

Therefore some things need to be known:
- If you rather want to use the IDE's feature, turn off the option in the Material Theme completely. This will prevent the background to be changed at start.
- If you want to disable the background completely, set the custom wallpaper path to be empty and the checkbox on, then click Apply, and finally uncheck the option and save.
- And same if you want to disable the plugin, it will not revert back the blank wallpaper, you will have to open the IDE's dialog.

-------------------

### Custom Accents

From version 0.7.0 the plugin can now have different accent colors! To change the Accent Color, just press on the *Accents* Action Button on the Toolbar/Quick Switch/Tools,
then select the desired accent color.

The Accent Color is changing:
- Active Tab Highlight Color (unless the custom tab highlight color is checked)
- Open folder icon
- Tool window icons hover color
- Modified file color
- Progress Bar, Active Input, Selected checkbox, Selected Radio Button
- Edited and Pinned file indicators
- Fold/Unfold icons
- Documentation links and bold texts
- Pressed Action Button Toggle state
- Autocomplete
- Quick info

---------------------

### Project View Decorators

Another feature taken from the Sublime Plugin is the ability to differentiate **opened directories** from closed ones. *"Opened directories"* actually mean 
directories from the project view whose one of their children is opened in the Editor.

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
- *Custom Accent Color*: Set a custom color for accent color

Component Settings:
- *Custom Wallpaper*: Set a custom wallpaper for the "no files opened" page
- *Material Design components*: Enable/Disable Material Design components (Buttons, Progress Bars...)
- *Material Icons*: Enable/Disable the Material Theme icons to the original ones
- *Material Theme*: Enable/Disable the material theme styles
- *Project View Decorators*: Enable/Disable the Project View Decorators. See the above section about separators.
- *Transparent Scrollbars*: Enable/Disable transparency in scrollbars

Keep in touch for new features!

---------------

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

---------------

## Contribution

Pull requests are appreciated! I can use some help on bugs and features listed in 
[https://github.com/ChrisRM/material-theme-jetbrains/projects/1], or you can send me some new ideas!

However, please note that this is a plugin dedicated to replicate the Sublime plugin, so it should coincide with its features and not deviate too much.

----------------

## Known Issues

### Material Wallpaper

Since the Material Wallpaper is making use of a somewhat hidden feature of the IDE allowing users to change their background wallpaper and is not providing its own,
 removing or disabling the plugin **does not** remove the wallpaper set by the plugin. Currently the implementation is trying its best to
  do so by caching the wallpaper set by the user at start, and restoring this wallpaper on exit, but if somehow the wallpaper is still 
  there despite having deleted/disabled the plugin, here is how to remove it:

- Close all open editors
- Open Command Palette (Cmd+Shift+A/Ctrl+Shift+A)
- Type "Set background image"
- Change/delete the background image

### Color Schemes copy

As stated in the Color Schemes section, whenever you change an attribute of the default color schemes a copy will be
created, and from then on you won't be able to receive updates to the color schemes. However, in some occasions, especially when using
third party plugins, the color schemes will be altered without your input, resulting in the aforementioned effect.

For instance, the _Markdown Navigator_ plugin creates a lot of properties when selecting a scheme, and even selecting
`Restore Defaults` will still result to creating a copy.

## Screenshots
#### Darker theme
![Darker theme](https://plugins.jetbrains.com/files/9377/screenshot_17081.png)

#### Default theme
![Default theme](https://plugins.jetbrains.com/files/9377/screenshot_17082.png)

#### Palenight theme
![Palenight theme](https://plugins.jetbrains.com/files/9377/screenshot_17085.png)

#### Lighter theme
![Lighter theme](https://plugins.jetbrains.com/files/9377/screenshot_17083.png)

#### Contrast Mode
![Choose theme](https://plugins.jetbrains.com/files/9377/screenshot_17080.png)

#### Custom Wallpaper
![Custom wallpaper](https://plugins.jetbrains.com/files/9377/screenshot_17084.png)

#### New features: Better dialogs, bigger statusbar, bigger tabs
![New features](https://plugins.jetbrains.com/files/9377/screenshot_17151.png)

#### Settings
![Settings](https://plugins.jetbrains.com/files/9377/screenshot_17086.png)

#### Monokai Theme
![Monokai Theme](https://plugins.jetbrains.com/files/9377/screenshot_17284.png)

#### Custom Theme Settings
![Custom Theme Settings](https://plugins.jetbrains.com/files/9377/screenshot_17285.png)

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
* [**Material Design Icons**](https://materialdesignicons.com/)
* [**Many FlatIcons authors**](https://www.flaticon.com/)

Thanks also for [@halacoglu](https://github.com/halacoglu/sublime-material-icon-pack) for the theme icons!

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

| Color             | Default / Darker |  Lighter   | 
| ---               | ---              |  ---       |
| Red               | `#FF5370`        |  `#E53935` |
| Pink              | `#f07178`        |  `#FF5370` |
| Orange            | `#F78C6C`        |  `#F76D47` |
| Yellow            | `#FFCB6B`        |  `#FFB62C` |
| Green             | `#C3E88D`        |  `#91B859` |
| Pale Blue         | `#B2CCD6`        |  `#8796B0` |
| Cyan              | `#89DDFF`        |  `#39ADB5` |
| Blue              | `#82AAFF`        |  `#6182B8` |
| Purple            | `#C792EA`        |  `#7C4DFF` |
| Violet            | `#bb80b3`        |  `#945EB8` |
| Brown             | `#ab7967`        |  `#ab7967` |


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

| Accent | Color |
| ----- | ----- |
| Turquoise   | `80CBC4` |
| Acid Lime   | `c6ff00` |
| Amethyst   | `ab47bc` |
| Aquamarine   | `64ffda` |
| Breaking Bad   | `388e3c` |
| Brick   | `e57373` |
| Coffee   | `80CBC4` |
| Cyan   | `00bcd4` |
| Daisy   | `FFEB3B` |
| Dodger Blue   | `2979ff` |
| Fuschia   | `E91E63` |
| Gold   | `FFD700` |
| Graphite   | `616161` |
| Indigo   | `3F51B5` |
| Lime   | `7CB342` |
| Orange   | `ff7042` |
| Pomegrenate   | `f44336` |
| Sky   | `84ffff` |
| Slate   | `607D8B` |
| Strawberry   | `ff4081` |
| Teal   | `009688` |
| Tomato   | `F44336` |

