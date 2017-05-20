# Changelog

## 0.3.7.2
- Fix Python Color schemes
- Fix Go Color Schemes
- New Python component to take the right file icon

## 0.3.7.1
- Fix next occurence wrong icon
- Restore accidently deleted edit icons
- C and CPP color schemes

## 0.3.7
- Add option to set bold tabs
- Add toggle options to Search Everything
- Change trees collapse and expand icons
- Themed IDE icons: Checkout, Project Structure, Back, Forward, History, Up, Down, Step Into, Step Out,
  Compile, Jars, Library...
- Other bug fixes and improvements

## 0.3.6.1
- Fix button background not taking the full width
- Themed Memory Indicator

## 0.3.6
- Add .pcss file icon [#25](https://github.com/mallowigi/material-theme-jetbrains/pull/25)
- Fix Console colors [#24](https://github.com/mallowigi/material-theme-jetbrains/pull/24)
- Align text and menu colors with the original theme

## 0.3.5
- Welcome Screen theming
- Progress Bar theming
- Striped tables theming
- Remove some borders added by Darcula
- Focused buttons are now distinguable
- Paint new breadcrumbs
- Restore plugin.xml icon

## 0.3.4.3
- Make buttons stand out a bit more anyway [#16](https://github.com/mallowigi/material-theme-jetbrains/issues/16)
- Fix Rider error [#17](https://github.com/mallowigi/material-theme-jetbrains/issues/17)
- Fix Ruby Colors schemes [#18](https://github.com/mallowigi/material-theme-jetbrains/issues/18)

## 0.3.4.2
- Add option in the settings to enable/disable the new buttons look

## 0.3.4.1
- Fix issue with Font Scale on HiDPI screens [#13](https://github.com/mallowigi/material-theme-jetbrains/issues/13)
- Put the active tab higlight on the left instead of the right when choosing Placement left.
- Messages bundle for easy replacing texts

## 0.3.4
- New Buttons! Now buttons look even more like the Sublime plugin!

## 0.3.3.3
- Add simple implementation of disabling the Material Theme (only the colors)
- Fix some colors again...

## 0.3.3.2
- Some colors were lost during the last update. This update should fix them.
- Fix custom font issue. 

## 0.3.3.1

### Fixes
- Fix issue with theme switcher when some parts of the UI do not update
- (hopefully) fix font apply

## 0.3.3

### Features
- Updated color schemes to reflect the Sublime plugin's color schemes
- New option: **Contrast mode**. This will allow you to apply a higher contrasted Look and feel
- Allow resetting the Active Tab settings with the default ones
- Updated file status colors: modified, added, ignored, conflicts...
- Replace the "asterisk" icon with an "*edit*" material icon
- Set Open and closed folders according to open tabs (still buggy)

## 0.3.2

### Features
- Now the active tab indicator is following the user's tabs placement settings!
- Started creating settings for customizing the plugin.
  - Active tab indicator color
  - Active tab indicator thickness

## 0.3.1

### Fixed
- Fixed issue with tabs in last EAP

## 0.3.0

### Features
- Update file icons to use the latest icons from the original Sublime theme repository.
- Added a new theme, the **Palenight** theme, that is a violet shade of the Default theme.

## 0.2.4

### Fixed
- Prevent file icons from being assigned to classes, methods, etc. - #285  @mjdetullio
- Separate psd icons from the images group - #292 @Freezystem
- Set parent scheme for dark themes to Darcula - #289 @vsch
- Load MT file icons before anything else - #292 @mallowigi
- Fix font overriding - #279 @mallowigi
- Context and menu lighter border - #281 

### Added
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
