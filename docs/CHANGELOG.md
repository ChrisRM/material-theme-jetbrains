## Changelog
----

## 6.8.0

### Features
- New Setting: **Overlays**: Add an overlay on the root pane that shows when popups are opened! (experimental)

### Fixes
- Refactor the Learning plugin's theming

### Other
- Convert resource lists to Kotlin

## 6.7.2

### Fixes
- Fix issue with notification showing all the time when the what's new window is disabled

## 6.7.1

### Fixes
- Fix titlebar issue in Windows

## 6.7.0

### Features
- Add **Project Icon** to the **Project Frame**
- Added themed [XPathView Plugin](https://plugins.jetbrains.com/plugin/12478-xpathview--xslt) configurations.
- **Language Additions**: Fix back `console`, `window`, `global` not being colored correctly. ([#1780](https://github.com/ChrisRM/material-theme-jetbrains/issues/1780))
- Refactor **File Status Colors**, allowing to restore the file status colors when resetting the color scheme ([#1732](https://github.com/ChrisRM/material-theme-jetbrains/issues/1732))
- Fix [#1773](https://github.com/ChrisRM/material-theme-jetbrains/issues/1773)
- Fix [#1764](https://github.com/ChrisRM/material-theme-jetbrains/issues/1764)
- Fix [#1776](https://github.com/ChrisRM/material-theme-jetbrains/issues/1776)
- Improve unused variables contrast ([#1775](https://github.com/ChrisRM/material-theme-jetbrains/issues/1775))

## 6.6.0
### Features
- New Set of Settings for the **Project Frame**:
  - Show Project Name in the Frame
  - Add the ability to specify a custom text
- Finally the so-long waited **Project Settings**. Now you can have specific settings on a per-project basis!
- Settings include:
  - Active Tab Color and Thickness
  - Uppercase bold tabs
  - Highlight Position
  - Project Frame's Color
  - Project Frame's Project Name display
  - Project Frame's Custom Text

### Fixes
- Patching light theme window borders (thanks [@Unthrottled](https://github.com/Unthrottled))
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1765>
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1768>

### Other
- Rewrite changelog to suit the changelog standard (<https://keepachangelog.com/en/1.0.0/>)


## 6.5.0
### Features
- Add new setting, **Inverted Selection Color**, to give a choice between the normal selection color and inverted selection color for the Autocompletion Popup.
- Finally add the Material Theme Settings to the **Sync Settings**. Now your settings and custom themes can be synced!
- Add a new action to display the **What's new Page** in the toolbar.

### Fixes
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1742>
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1744>
- Complete *GitHub* and *GitHub Dark* color schemes
- Fix fatal error with _CodeWithMe_ not starting

### Other
- Also, the **Accent Mode** settings moved to the **Features Tab** rather than **Components Tab**.

## 6.4.2
### Features
- Add setting to toggle the display of the _What's New_ page on each update.
- Set Autocomplete selected item to the same color as the background, and the list to the secondary color background.
- Remove Opacity for selected background color
- Improve contrast for some colors for themes: _Arc Dark, Github, Github Dark, Night Owl, Palenight, Lighter, Deep Ocean_
- Remove border color for striped tool windows

### Fixes
- Do not reset themed title bar's registry when using the free version
- Fix Actions and Wizard pages showing old locked settings, now included in the free version
- Fix Settings Search

## 6.4.1
### Fixes
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1743>
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1745>
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1739>

## 6.4.0
### Features
- Add _Track Colors_ to the _Scrollbars_ page.
- Add missing Android Studio color keys
- Theme the **Learn IDE** plugin's panes
- Theme the _Inlay Hints_
- Theme the _Inline refactoring_ toolbars
- Theme the Editor's _Problems_ Toolbar
- Theme the _blinking element_ in Color Scheme Pages

### Fixes
- Restore the _Activate License_ and _Buy License_ buttons
- Switching the _Striped Tool Windows_ ON will now ask for restart (but not the opposite action).
- Fix issue where applying the same theme as the current one would revert to Darcula
- Fix the duplicate elements when opening a project with the _Striped Tool Windows_ active.
- Fix NullPointerException in Rider (<https://github.com/ChrisRM/material-theme-jetbrains/issues/1734>)
- Fix Moonlight's identifier under caret color

## 6.3.3
### Features
- New action in the Quick Actions Panel to toggle _language additions_
-
### Fixes
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1722>
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1727>
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1728>

### Other
- More details about the new subscription model.

## 6.3.2
### Fixes
- Fix _What's new_ Window not showing

## 6.3.1
### Fixes
- Disable _Striped Tool Windows_ until it's more stable

## 6.3.0
### Features
- New feature: **Striped Tool Windows**
- New Color Scheme configuration screen: **Scrollbars**.
- New language additions for _JavaScript_:
  - Globals: `window`, `document`, `global`
  - Primitives: `NaN`
  - Keywords:
    - `abstract`, `class`, `extends`, `implements`
    - `async`, `await`
    - `constructor`
    - `static`
    - `get`, `set`
    - `if`, `else`, `for`, `while`, `do`
    - `in`, `of`, `instanceof`, `typeof`, `as`
    - `default`
    - `new`, `throw`
    - `return`, `yield`
    - `try`, `catch`, `finally`
  - Other: `prototype`
- New language additions for _TypeScript_:
  - Keywords:
    - `declare`, `namespace`
    - `enum`
    - `keyof`
    - `readonly`
    - `type`, `interface`, `alias`
  - Types:
    - `any`, `unknown`, `never`
    - `string`, `boolean`, `number`, `object`, `symbol`, `bigint`, `void`
- **Enforce highlighting*** setting to enforce language additions by making them look like weak warnings

### Fixes
- Outlined buttons have been slightly expanded to be more similar to their normal counterpart.
- The button highlighters have been slightly improved
- A new action have been added to the Quick Action Panels: _Outline Buttons_.
- Fix for [#1720](https://github.com/ChrisRM/material-theme-jetbrains/issues/1720)
- Fix for [#1725](https://github.com/ChrisRM/material-theme-jetbrains/issues/1725)


## 6.2.3
### Other
- I've listened to complaints and decided to extend the transition period until the end of April

## 6.2.2
### Fixes
- Fix the issue where there were too much tooltips on the settings home screen

### Other
- Add back **Tab Height**, **Compact Settings**, **Scrollbar Settings** and **Uppercase buttons** in the free plan.
- Fix Readme, add more information about _License activation_

## 6.2.1
### Fixes
- Fix alignment with Status Bar widget and remove option from settings
- Fix default font for console to be _JetBrains Mono_
- Fix hover color for outlined buttons

## 6.2.0
### Features
- New Setting: **Outlined Buttons**. Now buttons can have the more recent Material Design style.
- Add new UI properties support: Android ML, JetBrains Space, Got It Tooltip, Mnemonic Manager

### Fixes
- Improve the **Material Settings License Info** to contain more information, and a button to activate the license.
- Improve support for external native themes
- Prevents Color Scheme being reset to the theme's color scheme at start

### Other
- Prevents conflicts with other themes by adding the `(Material)` suffix to bundled themes.

## 6.1.2
### Fixes
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1697>

## 6.1.1
### Fixes
- Fix settings being overriden by migration to paid model

## 6.1.0
### Features
- Support for the new *Freemium* system:
  - Themes, Color Schemes and UI Components are **FREE**
  - Custom Themes, External Themes, Tweaks and Customization Settings are **PAID**
  - Please note that future themes will also only be available for Premium users.
- New action: **Activate License**

### Fixes
- Fix `$this` color in PHP
- Fix breakpoint line color in lighter themes
- Fix Title Bar font

## 6.0.0
### Other
- Switch to a new pricing model

## 5.7.0
### Fixes
- Support for 2021.1
- Fix _Material Wallpapers_

## 5.6.0
### Features
- **New Themes: Moonlight and Github Dark**

### Fixes
- Fix issue where you can't change file colors
- Fix <https://github.com/ChrisRM/material-theme-jetbrains/issues/1622>
- Change unused identifier colors
- Fix Welcome Screen left pane's selected color
- Reproduce Accent Color Tint to make icons benefit from accent colors
- Remove the native editor tabs underline, to use the Material one
- Fix Refactor variable white color on select
- Fix Checkboxes colors
- Add Diagram Colors to color schemes
- Fix issue where MTButton would make the GPU go over 50%

## 5.5.0
### Fixes
- Reworked MTCheckBoxUI to work with icons instead of graphics drawings
- Fix issue where Solarized Themes were not persisted
- Convert Custom Theme Settings to a striped table
- Add _CodeWithMe_ resources and fix some other theme resources
- Add new hack to make Atom Material Plugin work with latest EAP (thanks @Unthrottled)

### Other
- Nest Custom Theme Settings under the Material Theme Settings

## 5.4.2
### Fixes
- Fix menu popups' active indicator
- Fix list and table hover background

## 5.4.0-5.4.1
### Other
- Basic support for 2020.3 EAP (Work in progress)

## 5.3.4
### Fixes
- Fix Transparent title bars appearing on dialogs
- Fix deprecated new screen active link color
- Update new color schemes keys: Inactive template and Regexp matching groups

## 5.3.3
### Fixes
- Support for 2020.2 EAP
- Add Accent Color for Searchable Settings
- Reduced opacity in project frame colors

## 5.3.2
### Features
- Add a button to stop showing the Atom Material Icons plugin notification
- Improve colors schemes: Add better coloring to inline Java documentation renderer
- Colorize local history labels
- Colorize scopes and bookmarks colors
- Replace global colors with colors from the themes (experimental)

### Fixes
- Fix default highlighted reference color
- Fix theme selection from the wizard not being persisted

## 5.3.1
### Fixes
- Fix NPE when opening file colors with a Native Theme

## 5.3.0
### Features
- New Language Addition: **Kotlin**!
    - Keywords: `private`, `public`, `protected`, `internal`
    - Keywords: `sealed`, `open`, `override`
    - Keywords: `object`, `companion`
    - Keywords: `import`, `package`
    - Keywords: `data`
    - Keywords: `operator`, `infix`
    - Keywords: `this`, `super`
    - Primitives: `null`, `Unit`
    - Primitives: `true`, `false`
- New settings for PHP Additions:
    - `echo`, `true`, `false`, `null`, `exit`, `die`
- New settings for TypeScript additions:
    - `declare`, `type`, `alias`, `true`, `false`
- New settings for JavaScript additions:
    - `true` and `false`
- New settings for Java additions:
    - `true` and `false`

### Other
- Improved _Language Additions Color Pages_: now properties are grouped in the settings page

### Fixes
- Syntax highlightings improvements for Kotlin
- Fix Parameter Info Background color for Kotlin
- Fix File Colors not applied in Inactive tabs
- Add opacity during Live Templates completion

## 5.2.0
### Features
- New Feature: **Project Frame Color**: Add a colored stripe on top of each project for easier visualization. _Inspired by [Unique Window Colors for VSCode](https://marketplace.visualstudio.com/items?itemName=stuart.unique-window-colors)._
- **New**: The active tab color is now taken from the **color scheme** if the "Active Tab Color" is not enabled.
- Add new actions to toggle Material Wallpapers and Project Frame Colors
- Add new action to download the *Atom Material Icons* Plugin.
- The Material Status Bar indicator is now togglable just like other Status Bar indicators.

### Fixes
- Fix Loading and Saving Custom Themes
- Fix Material Oceanic scrollbars color.
- Other fixes and improvements

## 5.1.3
### Fixes
- Fix Saving custom theme

## 5.1.0
### Features
- Refactor file status colors to not modify Darcula, therefore cleaning up after uninstall
- Make the floating toolbars rounder and using the theme colors
- Add support for Rainbow Brackets, GitToolbox and Markdown Viewer in color schemes
- Add more visibility to the Atom Material Icons plugin

### Fixes
- Fix Theme not being persisted after restart
- Other fixes and improvements

## 5.0.0
### Features
- Built-in support for **native themes**! Supports `Light`, `Darcula`, `High Contrast` and user-defined themes!
    - Please note that this is a feature in its first steps, so please report any bugs you can find!
- New feature: **Material Wallpapers**! Now themes come with their own empty frame wallpaper!

### Removals
- Deprecation of the following features:
    - _Material Design Components_ -- This is replaced by native themes support
    - _Material Theme_ -- This is replaced by native themes support
    - _Material Icons (UI, File and PSI Icons)_ -- This has been moved to the [Atom Material Icons Plugin](https://plugins.jetbrains.com/plugin/10044-atom-material-icons)
    - _Arrow Styles_ -- This has been moved to the [Atom Material Icons Plugin](https://plugins.jetbrains.com/plugin/10044-atom-material-icons)

For more changelog entries, check <https://material-theme.com/docs/changelog/>
