# Changelog

## [Unreleased]

## 0.2.1
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

[Unreleased]: https://github.com/ChrisRM/material-theme-jetbrains/compare/v0.1.9...HEAD