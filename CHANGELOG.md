# Changelog

## 1.3.0
- New option: **Light Custom Theme**: Use custom colors with IntelliJ Look and Feel. Used for Light themes.
- Fix Custom Themes styling issues.
- Change light themes notification colors.
- Rename Material Default to **Material Oceanic**
- Automatically change color scheme when switching themes
- Add alert to reset custom theme colors when switching Look and Feel
- Change instance fields, static fields and properties colors from Red to White as it is confused with errors.
- Add update notifications.

## 1.2.1
- Fix issue #193

## 1.2.0
- Add new UI Icons for Structure and Web Deployment sections
- New feature: Accent Scrollbars
- Changed Active Tab Highlight to display Tab File Colors if defined, at the cost of having a transparent overlay
- Added Markdown navigator color scheme
- Added Browse Word at Caret plugin colors
- Improve File Colors performance by initializing colors statically (thanks @denofevil)
- Set default ignored file colors to Brown
- Set default scratch colors to default text

## 1.1.2
- Atom One Dark Color Scheme

## 1.1.1
- XCode Development file icons
- Travis file icons

## 1.1.0
- Adding Resharper Hint and Suggestion Colors, improved Unknown Variable color, Debugger Colors and Link Colors
- Updated Lighter scheme default color to be like VSC Lighter Theme
- Improved Lighter Theme Foreground Color
- Fix annotations

## 1.0.2
- Starting writing documentation
- Optimization improvements

## 1.0.0
- Release

## 0.12.0
- New components: slider and radio buttons

## 0.11.0
- Targeting 2017.3

## 0.10.6
- Fix ComboboxAction and IdeaButtonLookAction in 2017.3
- Set default color for "NOT CHANGED" file status
- Set directories color from "Up to date" file status
- Fix issue with Run Configurations
- Fix Scratches Color in the tree

## 0.10.5
- Bug fixes due to the passage to 2017.3

## 0.10.4
- Fix Tabbed Pane color in Arc and One Dark
- Fix background color of list selected items in One Dark
- Add babelrc.json to babel icon association
- Fix (hopefully) the NullPointerException due to not being recognized File Colors

## 0.10.3
- Material One Dark Color Scheme

## 0.10.2
- Atom One Dark Theme
- Move icons outside of fileIcons for better "disable Material Icons"

## 0.10.1
- Fix issue with module directories taking the default icon in PyCharm
- Added icons for the theme switcher (thanks @halacoglu https://github.com/halacoglu/sublime-material-icon-pack)
- Rework MTWallpaper Component to fix remaining issues
- Fix accents not being loaded with Material Theme disabled
- Improve Arc Dark Theme

## 0.10.0 (alpha)
- Two new themes: Monokai and Arc Dark
- Custom Theme Support (alpha - please read README)

## 0.9.4
- Improvements of Lighter Theme. Now Lighter Theme extends IntelliJ Look And Feel instead of Darcula.
- Fix issues with No Material Theme that triggered some NPEs
- Changed Tree foreground color for Lighter Theme
- Added more UI Icons 

## 0.9.3
- Fix issue with projects open (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/145)
- Possibly fix issue with 100% CPU (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/143) 
- Fix import icon size (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/147)
- Change deprecated color (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/151)
- Improve Disabled Material Theme

## 0.9.2
- Fix issue with File colors for Psi Icons
- Fix issue with CPU 100% usage
- Fix issue with breakpoint icon size

## 0.9.1.2
- Fix issue with tool window icons in retina
- Add more ui icons

## 0.9.1
- Remove uppercase bold tabs as a default and fix issue with project settings persistence
- Revert fix of debugger tab height as it breaks other tabs

## 0.9.0
- Add custom accent color configuration
- Add Uppercase bold tabs to look more like MDTabs
- Add Compact Sidebar height configuration
- Reduce minimal length of tabs to 18
- Add a border to the editor when tabs placement is left or right
- More UI Icons

## 0.8.1
- Fix issue with Contrast action button not working
- Fix issues with not themed popups
- Add accent color to non-themed search bars
- Add background color to search result

## 0.8.0.2
- Fix issue with custom file statuses not being colorable
- Themed Material Combobox Action button 

## 0.8.0
- Fix Checkbox style in Material Components disabled
- Allow customization of file status colors
- Rewrite Wallpaper component to unset the wallpaper on exit
- Fix height of debug tabs
- More UI Icons

## 0.7.3.2
- Changed javascript instance member color
- Changed javascript icon

## 0.7.3
- Material Checkboxes

## 0.7.2.1
- Fix issue with file colors (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/105)
- Fix issue with line highlight accent (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/104)

## 0.7.2
- Convert old "Bold tabs option" into "bold directories"
- Tree colors like the sublime theme
- Selected Tree Line like in the Sublime Theme
- Set opened folders with accent theme like in Sublime
- Add PHP, Python and more General UI Icons

## 0.7.1.2
- Theme the VCS Log Merge and Own Commits
- Better colors for selected Tool Window buttons

## 0.7.1
- Fix Hide File Icons
- Fix Objective C and Coffeescript colors
- Set TabsHeight as scalable for High DPI
- Removing bold tabs support

## 0.7.0.3
- Changing accent colors now update icons as well (needs close project)
- VCS icons
- Fix Status Indicator cut on Windows (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/89)
- Fix Project Icon as accent hoverable (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/88)
- Revert default font to 12px since it makes the text too big (blur is okay i guess)
- Fix unreadable font (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/98) 

## 0.7.0.2
- Fix contrast mode not applying (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/84)
- Fix issues with Action buttons (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/93)
- Set Roboto default size to 14px (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/87)
- Fix Custom Tree Indent (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/92)

## 0.7.0
- Padded table rows
- Material Design Number Inputs (with disabled support)
- Material Design Dropdowns (Comboboxes)
- Material Design Action Buttons Style
- Add more transparency to Tree Selections
- Support for Accent Colors!
- More UI Icons
- Fix Tool Window Issue (https://github.com/mallowigi/material-theme-jetbrains-eap/issues/82)

## 0.6.0.1
- Disable PHP file association and instead make use of PHP Psi Icons

## 0.6.0
- Fix issue with Merge branches window
- Put Material Theme options under "Appearance"
- Add Actions for Settings: Compact Sidebar, Compact Statusbar, Material Theme, Material Components, Material Icons, Status Bar Indicator and Project View Decorators
- Add actions for Hide File Icons, Bigger Tabs and set actions as toggles
- Themed Action UI Icons, General UI Icons and Debugger UI Icons
- Refactor classes so we can make use of Actions.
- Inserted Checkstyle and Copyright

## 0.5.3
- Customizable status bar height
- Replace Loader when opening big files with Material one
- Make better colors for Memory Indicator for Darker, Lighter and Palenight
- Fix issue with caret (#379)
- Add docker compose yaml association (thanks @thaffenden)
- Other fixes

## 0.5.2
Fix many issues related to Lighter theme:
- Breakpoint colors
- Autocomplete
- Tree colors
- Notifications color
- Progress bar
- Memory indicator
- Scrollbars
- Also removed None theme since we can simply uncheck the option in the settings

## 0.5.1
Small update so I can have feedback about possible bugs
- Material Headers (experimental)

## 0.5.0
This version is more focused on the UI:
- Themed Scrollbars
- Material Table Headers
- Material Inputs (not applicable everywhere at the moment)
- Material Passwords (with option of showing passwords)
- Material Tabs
- Add option to disable Material Theme only (leaving components, tabs and other options on)
- Tab Height customizable
- Notification colors
- Custom Tree Indents
- Better Dialog titles
- More UI Icons
- Bug fixes (and possibly improvements)

## 0.4.4
- Added remaining options to TopHitProvider
- Tinted Icon implementation
- Redesigned folders to suit the MT icons (though help is appreciated)
- Added custom file colors (Scope)
- Add theme changer to Quick Switch (Ctrl+~)
- More UI Icons

# 0.4.3
- Fix issue with Statusbar option not being saved
- Add bigger file icons for original ones (sass, php, ruby...)
- More file icons: Chef, Cucumber, EJS, Jinja, Freemarker, PHPunits, Typings, Visio, VS
- Hide exceptions about IndexOutOfBounds
- Show red icon for excluded open files
- Remove old PSI icons to use IntelliJ's 
- Change pin icons (alpha)
- Better icons for expand and collapse (alpha)
- Fix bad associations

## 0.4.1
- Change behavior of custom wallpaper to not disable the background if the user unchecks the option, allowing to set custom wallpaper with the IDE
- Add Disable option for the current theme indicator in the status bar
- Fix issue with SQLite icon
- Theme notification popups
- Add Indicator in Status Bar for current theme
- Fix some filelist icons

## 0.4.0
- A lot more file icons!
- Fix partly syntax highlight for HOCON, Groovy, ERB, Scala, Kotlin and Hibernate
- Fix issue with light color schemes not being saved
- Fix issue with Background getting overriden
- Fix issue with settings not being saved
- Fix #92
- Fix: Do not try to replace all icons, if an icon is not provided use Jetbrains one.
- Refactor project: put the selected theme in the MTConfig + better folder structure
- Add more icons: Access, Word, Powerpoint, Excel, Audio, Video, Elm, Go, Hack, Julia, Pug, Kotlin, Maya, Markup, Premiere, Powershell, Rake, Riot, Stata, Sublime, Vim
- Fix PHP + JS Code Schemes
- New: Background image for empty IDE with customization available in options
- New: Option to hide file icons in the Project View
- New: Option to disable Material Icons
- New: Option to disable Project View decorators
- New: Option to select compact (and not compact) project view
- New icons: Akka, Ada, Android
- New UI Icons
- Progress indicators
- Set light version of contrast mode (though it needs to be in IntelliJ LAF)
- Revert better contrast as it breaks theme switching :'(
- Code coverage colors
- Notification, Information and Documentation popups
- Quick info theming
- Parameter info theming
- Smart completion theming
- Documentation popup theming
- Better contrast mode
- Accent tab close button

## 0.3.0.1
### Fixes
- Fix association for Angular files
- Fix tests

## 0.3.0.0

Thanks to @mallowigi for taking the project to the next level. This is all him: 
- Code coverage colors
- Notification, Information and Documentation popups
- Fix Python Color schemes
- Fix Go Color Schemes
- New Python component to take the right file icon
- Fix next occurence wrong icon
- Restore accidently deleted edit icons
- C and CPP color schemes
- Add option to set bold tabs
- Add toggle options to Search Everything
- Change trees collapse and expand icons
- Themed IDE icons: Checkout, Project Structure, Back, Forward, History, Up, Down, Step Into, Step Out,
  Compile, Jars, Library...
- Other bug fixes and improvements
- Fix button background not taking the full width
- Themed Memory Indicator
- Add .pcss file icon 
- Fix Console colors
- Align text and menu colors with the original theme
- Welcome Screen theming
- Progress Bar theming
- Striped tables theming
- Remove some borders added by Darcula
- Focused buttons are now distinguable
- Paint new breadcrumbs
- Restore plugin.xml icon
- Make buttons stand out a bit more anyway
- Fix Rider error 
- Fix Ruby Colors schemes 
- Add option in the settings to enable/disable the new buttons look
- Fix issue with Font Scale on HiDPI screens 
- Put the active tab higlight on the left instead of the right when choosing Placement left.
- Messages bundle for easy replacing texts
- New Buttons! Now buttons look even more like the Sublime plugin!
- Add simple implementation of disabling the Material Theme (only the colors)
- Fix some colors again...
- Some colors were lost during the last update. This update should fix them.
- Fix custom font issue. 
- Fix issue with theme switcher when some parts of the UI do not update
- (hopefully) fix font apply
- Updated color schemes to reflect the Sublime plugin's color schemes
- New option: **Contrast mode**. This will allow you to apply a higher contrasted Look and feel
- Allow resetting the Active Tab settings with the default ones
- Updated file status colors: modified, added, ignored, conflicts...
- Replace the "asterisk" icon with an "*edit*" material icon
- Set Open and closed folders according to open tabs (still buggy)
- Now the active tab indicator is following the user's tabs placement settings!
- Started creating settings for customizing the plugin.
  - Active tab indicator color
  - Active tab indicator thickness
- Fixed issue with tabs in last EAP
- Update file icons to use the latest icons from the original Sublime theme repository.
- Added a new theme, the **Palenight** theme, that is a violet shade of the Default theme.
- Prevent file icons from being assigned to classes, methods, etc. - #285  @mjdetullio
- Separate psd icons from the images group - #292 @Freezystem
- Set parent scheme for dark themes to Darcula - #289 @vsch
- Load MT file icons before anything else - #292 @mallowigi
- Fix font overriding - #279 @mallowigi
- Context and menu lighter border - #281 
- Add file association for `.yaml` files - #297 @thaffenden
- Add Dart icon - #296 @seanjohnite
- Add `htpasswd` to htaccess group - @Freezystem
- Add icons for `package.json, gruntfile, gulpfile` and `webpack` - @mallowigi
- Set better color for Parameter hints - @mallowigi

## 0.2.3

### Changed
- Updated Gradle wrapper

### Added
- File icon: Rust `*.rs` - #240
- File icon: ES/ES6 `*.es|*.es6` - #240

## 0.2.2

### Fixed
- Android Studio fix. #220
- No need to override the application icons, so those are deleted.
- Fixes UI icons for recent versions of platform. @mallowigi
- Issue #258 fix crashes on Windows for IntelliJ IDEA 2016.3. @bulenkov
- More sensible colors for diffs. @hypnoglow

## 0.2.1

### Fixed
- Hopefully a final fix for issue #205, IDEA-157843 and IDEA-156327

## 0.2.0

### Fixed
- Improved visibility for diffs on default color scheme. Thanks @marvhock - #208
- Hopefully fixed issue #205, [IDEA-157843](https://youtrack.jetbrains.com/issue/IDEA-157843) and [IDEA-156327](https://youtrack.jetbrains.com/issue/IDEA-156327)
- Merged recent changes made to the platform

### Added
- SidePanel background color (Preferences)

## 0.1.9

### Fixed
- Fails to launch when using Darker or Lighter theme. Thanks @robertfreund - #187
- Kotlin syntax highlighting - #153

### Added
- File icon: Kotlin - `*.kt`
- File icon: Liquid - Shopify templating language - `*.liquid`
- File icon: Lua - `*.lua`
- File icon: LICENSE files

## 0.1.8

### Fixed
- In some cases getVirtualFile() for psi elements can be null. Fixes #172, #175 - @anstarovoyt
- Vertical tabs highlight - @Cyberdelia1987
- Fixes an error which caused the plugin to misbehave in non-java ide's. See #177

### Added
- File icon: React `*.jsx` - #162
- File icon: PHTML `*.phtml`
- File icon: Gradle `*.gradle` - #140
- File icon: Text `*.txt`

## 0.1.7

### Added
- This changelog
- Identifier under caret - Error Stripe Mark

### Changed
- Don't grab blade files with PHP regex

### Fixed
- Fix issues with the color schemes.

[Unreleased]: https://github.com/ChrisRM/material-theme-jetbrains/compare/v0.2.2...HEAD
