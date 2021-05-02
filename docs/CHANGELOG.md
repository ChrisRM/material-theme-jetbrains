# Changelog
----
# 6.4.0
- Features:
  - Add _Track Colors_ to the _Scrollbars_ page.
  - Add missing Android Studio color keys
  - Theme the **Learn IDE** plugin's panes
  - Theme the _Inlay Hints_
  - Theme the _Inline refactoring_ toolbars
  - Theme the Editor's _Problems_ Toolbar
  - Theme the _blinking element_ in Color Scheme Pages
- Fixes:
  - Restore the _Activate License_ and _Buy License_ buttons
  - Switching the _Striped Tool Windows_ ON will now ask for restart (but not the opposite action).
  - Fix issue where applying the same theme as the current one would revert to Darcula
  - Fix the duplicate elements when opening a project with the _Striped Tool Windows_ active.
  - Fix NullPointerException in Rider (#1734)
  - Fix Moonlight's identifier under caret color

# 6.3.3
- Fix #1722, #1727 and #1728
- New action in the Quick Actions Panel to toggle _language additions_
- More details about the new subscription model.

# 6.3.2
- Fix What's new Window not showing

# 6.3.1
- Disable Striped Tool Windows until it's more stable

# 6.3.0
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
- Outlined buttons have been slightly expanded to be more similar to their normal counterpart.
- The button highlighters have been slightly improved
- A new action have been added to the Quick Action Panels: _Outline Buttons_.
- Fix for [#1720](https://github.com/ChrisRM/material-theme-jetbrains/issues/1720)
- Fix for [#1725](https://github.com/ChrisRM/material-theme-jetbrains/issues/1725)


# 6.2.3
- I've listened to complaints and decided to extend the transition period until the end of April

# 6.2.2
- Fix the issue where there were too much tooltips on the settings home screen
- Add back **Tab Height**, **Compact Settings**, **Scrollbar Settings** and **Uppercase buttons** in the free plan.
- Fix Readme, add more information about License activation

# 6.2.1
- Fix alignment with Status Bar widget and remove option from settings
- Fix default font for console to be JetBrains Mono
- Fix hover color for outlined buttons

# 6.2.0
- New Setting: **Outlined Buttons**. Now buttons can have the more recent Material Design style.
- Improve the **Material Settings License Info** to contain more information, and a button to activate the license.
- Improve support for external native themes
- Prevents Color Scheme being reset to the theme's color scheme at start
- Prevents conflicts with other themes by adding the `(Material)` suffix to bundled themes.
- Add new UI properties support: Android ML, JetBrains Space, Got It Tooltip, Mnemonic Manager

# 6.1.2
- Fix https://github.com/ChrisRM/material-theme-jetbrains/issues/1697

# 6.1.1
- Fix settings being overriden by migration to paid model

# 6.1.0
- Support for the new *Freemium* system:
  - Themes, Color Schemes and UI Components are **FREE**
  - Custom Themes, External Themes, Tweaks and Customization Settings are **PAID**
  - Please note that future themes will also only be available for Premium users.

- New action: **Activate License**
- Fix $this color in PHP
- Fix breakpoint line color in lighter themes
- Fix Title Bar font

# 6.0.0
- Switch to a new pricing model

# 5.7.0
- Support for 2021.1
- Fix Material Wallpapers

# 5.6.0
- **New Themes: Moonlight and Github Dark**
- Fix issue where you can't change file colors
- Fix #1622
- Change unused identifier colors
- Fix Welcome Screen left pane's selected color
- Reproduce Accent Color Tint to make icons benefit from accent colors
- Remove the native editor tabs underline, to use the Material one
- Fix Refactor variable white color on select
- Fix Checkboxes colors
- Add Diagram Colors to color schemes
- Fix issue where MTButton would make the GPU go over 50%

# 5.5.0
- Reworked MTCheckBoxUI to work with icons instead of graphics drawings
- Fix issue where Solarized Themes were not persisted
- Convert Custom Theme Settings to a striped table
- Nest Custom Theme Settings under the Material Theme Settings
- Add CodeWithMe resources and fix some other theme resources
- Add new hack to make Atom Material Plugin work with latest EAP (thanks @Unthrottled)

# 5.4.2
- Fix menu popups' active indicator
- Fix list and table hover background

# 5.4.0-5.4.1
- Basic support for 2020.3 EAP (Work in progress)

# 5.3.4
- Fix Transparent title bars appearing on dialogs
- Fix deprecated new screen active link color
- Update new color schemes keys: Inactive template and Regexp matching groups

# 5.3.3
- Support for 2020.2 EAP
- Add Accent Color for Searchable Settings
- Reduced opacity in project frame colors

# 5.3.2
- Add a button to stop showing the Atom Material Icons plugin notification
- Improve colors schemes: Add better coloring to inline Java documentation renderer
- Fix default highlighted reference color
- Colorize local history labels
- Colorize scopes and bookmarks colors
- Replace global colors with colors from the themes (experimental)
- Fix theme selection from the wizard not being persisted

# 5.3.1
- Fix NPE when opening file colors with a Native Theme

# 5.3.0
- New Language Addition: **Kotlin**!
    - Keywords: private, public, protected, internal
    - Keywords: sealed, open, override
    - Keywords: object, companion
    - Keywords: import, package
    - Keywords: data
    - Keywords: operator, infix
    - Keywords: this, super
    - Primitives: null, Unit
    - Primitives: true, false
- New settings for PHP Additions:
    - echo, true, false, null, exit, die
- New settings for TypeScript additions:
    - declare, type, alias, true, false
- New settings for JavaScript additions:
    - true and false
- New settings for Java additions:
    - true and false
- Improved Language Additions Color Pages: now properties are grouped in the settings page
- Syntax highlightings improvements for Kotlin
- Fix Parameter Info Background color for Kotlin
- Fix File Colors not applied in Inactive tabs
- Add opacity during Live Templates completion

# 5.2.0
- New Feature: **Project Frame Color**: Add a colored stripe on top of each project for easier visualization. _Inspired by [Unique Window Colors for VSCode](https://marketplace.visualstudio.com/items?itemName=stuart.unique-window-colors)._
- Fix Loading and Saving Custom Themes
- **New**: The active tab color is now taken from the **color scheme** if the "Active Tab Color" is not enabled.
- Add new actions to toggle Material Wallpapers and Project Frame Colors
- Add new action to download the *Atom Material Icons* Plugin.
- Fix Material Oceanic scrollbars color.
- The Material Status Bar indicator is now togglable just like other Status Bar indicators.
- Other fixes and improvements

# 5.1.3
- Fix Saving custom theme

# 5.1.0
- Refactor file status colors to not modify Darcula, therefore cleaning up after uninstall
- Make the floating toolbars rounder and using the theme colors
- Fix Theme not being persisted after restart
- Add more visibility to the Atom Material Icons plugin
- Add support for Rainbow Brackets, GitToolbox and Markdown Viewer in color schemes
- Other fixes and improvements

# 5.0.0
- Built-in support for **native themes**! Supports Light, Darcula, High Contrast and user-defined themes!
    - Please note that this is a feature in its first steps, so please report any bugs you can find!
- New feature: **Material Wallpapers**! Now themes come with their own empty frame wallpaper!
- Deprecation of the following features:
    - _Material Design Components_ -- This is replaced by native themes support
    - _Material Theme_ -- This is replaced by native themes support
    - _Material Icons (UI, File and PSI Icons)_ -- This has been moved to the [Atom Material Icons Plugin](https://plugins.jetbrains.com/plugin/10044-atom-material-icons)
    - _Arrow Styles_ -- This has been moved to the [Atom Material Icons Plugin](https://plugins.jetbrains.com/plugin/10044-atom-material-icons)

For more changelog entries, check <https://material-theme.com/docs/changelog/>
