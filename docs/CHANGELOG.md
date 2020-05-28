# Changelog
----
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