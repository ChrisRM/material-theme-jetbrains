# Changelog

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
