# Changelog

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
