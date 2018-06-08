# Changelog
----
# 2.4.1
- Add None Option to arrow styles
- Fix Sky accent action
- Remove shadows on balloons

# 2.4.0.4
- Fix fatal error in older IDEs
- Fix blurry arrow icons
- Fix theme not being applied for some themes (notably custom)
- Set Font Size HiDPI aware
- Add missing options from the Settings search
- Allow setting Material Theme options from Statusbar widget
- Add multicaret icons

# 2.4.0.3
- Add font size to be toggleable
- Set Menlo as primary font for Lighter Schemes, and Fira Code for Darker schemes

# 2.4.0
- Reworked the settings page to group options into tabs and allow changing theme from there
- Setting for changing the font size of the Project View (needs restart)
- Setting for disabling Material File Status Colors
- Improve contrast of Lighter Theme
- Improved a bit the Comboboxes to look like Material Design ones
- Fix Tabbed Pane colors in Lighter Themes
- Add analytics
- Add TSX icon

# 2.3.3
- This is a quick fix for 182.2757. This fixes critical issues introduced by the latest EAP.
There could be other bugs though.

# 2.3.2
- Fix error from plugin.svg icon not found
- Fix preview icon similar to details icon

# 2.3.1 (alpha)
- **NOTE: THIS IS NOT A STABLE RELEASE!**
- This is a quick fix for MTComboBoxes to get back wide dropdowns.
- This is not a final fixes since it doesn't display the paddings for some comboboxes (such as Color Scheme), even though it works after selecting another value. This will be investigated further in a next release.
- This also brings the popup over the dropdown to imitate material design components. This is disabled by the Compact Dropdown settings though

# 2.3
- Major overhaul of the icons: most icons have been converted to svg! Please note though:
  - I couldn't find the originals of some icons, so some icons are now different or with different colors (sorry JSON...)
  - Since this is a big update, there might be some further changes to those icons
  - I decided to keep some original icons made by Jetbrains (such as Minimize Tool Window)
  - Please open issues about icons that you find not suited for their purpose.
- Set a default matched brace color in order to use _Current Scope highlight_ (https://github.com/ChrisRM/material-theme-jetbrains/issues/774)
- Fix bugs related to 2018.2 EAP (https://github.com/ChrisRM/material-theme-jetbrains/issues/777)

## 2.2.3
- Theme Coding Style and Debugger tabs
- Add angular.json association

## 2.2.2
- Increase size of Material Arrows to 11x11
- Fix Active Tab Highlight color to take accent color
- Increase left padding of tree rows
- Change color of primary buttons for Monokai theme
- Set Autocomplete selected item background color when unfocused
- Change notification colors (success, warn, error)

## 2.2.1
- Theme border color of popups
- Fix issue with SVG Viewer
- Fix Decorated Folders
- Fix Uppercase bold tabs
- Fix MTNavBar memory leak
- Fix Darcula/IntelliJ UIResources
- Better Arrows visibility
- Fix file colors not being set
- Add more tests

## 2.2.0
- **DataGrip** Icons!
- Solarized Dark and Light color schemes
- Improve Lighter Theme contrast and texts
- Improve file colors for Monokai and Solarized
- Extract ActionButtons into their own component

## 2.1.7
- Fix command line icons
- Reenable Darcula components when Material Components are disabled
- Apply monochrome filter on activate
- Change "Modified on non active changelist" and "added on non-active changelist" colors

## 2.1.6
- Improve Solarized Dark
- Set arrows color the same as the folders color
- PHP Icons
- Python Icons
- Fix undefined notification colors
- Remove InternalDecorator hack until Jetbrains fix this

## 2.1.5
- New Component: Material NavBar
- Set folder associations just like [Material Icons VSCode](https://github.com/PKief/vscode-material-icon-theme)
- Center not centered folder icons
- Fix Action Combobox for Lighter Themes

## 2.1.4
- Change background color of Solarized Light
- Change foreground color of buttons in Light themes

## 2.1.3
- Fix Background color for PHP code in some themes
- Set the "splitter" color as a "secondaryBorder" resource (Fix #659)
- Fix "Tree Selected Item" artifact

## 2.1.2
- Revert issues with 2.1.1

## 2.1.1
- Bug fixes and performance improvements

## 2.1.0
FEATURES:
- New Themes: **Solarized Dark and Light**!

FIXES:
- Fix issue with Bundled Themes not being persisted
- Set Arrows Color to be the same as the folder color
- Add new darcula Button resources to the Abstract Theme

CHORE:
- Rename "Inactive Resources" to "Button Resources"

## 2.0.0
FEATURES:
- Complete rewrite of the Theming System! Now all themes inherit from the same parent class which defines which resources get which color. Maintaining themes will be a lot easier!
- **Plugin extension** to allow plugin developers to add their own themes to the Material Theme plugin! It's still in beta but some examples will be available shortly.
- The caret is now an **accent color** resource.
- The Arrows Style applies now to menus as well
- RubyMine icons for Ruby/Rails folders and actions.

FIXES:
- Remove the "Button Highlight color" and "Caret" property from Custom Themes
- Checkboxes and RadioButtons are now a bit better.
- TabbedPanes and TextArea are now using the MaterialTheme namespace.
- Fix the Black Line separating tool windows from the editor
- Fix the search inside lists and menus
- Improvements to the themes
- Bug fixes and improvements.

Please note that this is a beta version, it is now entering the test phase and might contain bugs.

## 1.8.2
FEATURES:
- Add Non-Project Files default color to the current theme's disabled color
- New **Material Toggle Button UI**
- Fix breadcrumb and parameter hint colors to their relevant theme colors
- Add a specific accent color for each color scheme:
    - Oceanic: Teal
    - Darker: Orange
    - Lighter: Indigo
    - Palenight: Amethyst
    - Monokai: Lime
    - One Dark: Gold

FIXES:
- Fix **performance issues** by removing unused code and refactoring _Monochrome Icons_
- Fix tooltip colors

OTHER:
- Installed Test framework

## 1.8.1
FEATURES:
- Set Accent color to modified settings
- Tool Window header color styles
- Add browserslist icon
- Add CNAME icon

FIXES:
- Fix Monochrome Icons to cover the whole IDE
- Fix TextField UI to include the magnifier for search fields
- Fix Font in Text Fields to use the color scheme's font
- Fix Action button radius
- Brighten primary buttons to be more differentiable
- Change React default icon

OTHER:
- Add Acknowledgements in the documentation
- Add new url for the documentation: www.material-theme.com

## 1.8.0
FEATURES:
- Add different angular icons by type: service, component, directive, pipe and routing
- Add postcssrc and postcssconfig icons
- New feature: **Compact Dropdowns**. Reduce the size of dropdowns and their items.
- New feature: **Monochrome Icons**. Now you can decide to have icons desaturated with the color of the current theme
- New feature: **Active tab highlighting** for Lighter themes!
- New feature: **Tab Opacity**: allow to customize the opacity of the coloring of inactive tabs.
- New feature: **Uppercase buttons**
- Theme dialog title color when inactive
- Go to the documentation from the settings' help buttons

FIXES:
- Fix Tooltips color
- Fix Accent scrollbars on Mac
- Replace many UI Icons for the nodes
- Fix issues with Palenight theme
- Fix and replace Option Button

## 1.7.0.4
- Fix Notifications
- Fix tooltip colors

## 1.7.0.3
- Fix focus color on search boxes
- Change debugger colors and tooltip colors

## 1.7.0.2
- Fix https://github.com/ChrisRM/material-theme-jetbrains/issues/436

## 1.7.0
- Upgrade to 2018.1
- Fix checkboxes
- Fix textfields
- Fix comboboxes
- Fix buttons

## 1.6.1
- Angular icon associations for services, pipes, directives and routes
- PostCss config associations

## 1.6.0.4
- Fix https://github.com/ChrisRM/material-theme-jetbrains/issues/436

## 1.6.0.3
- Change default option of title bar to false
- Add more CLion icons
- Fix background color of the "New Project" sidepanel
- Alert when setting title bar

## 1.6.0.2
- Fix Dark Title bar option not applying on Windows
- Fix theming not applying to menus
- Clion icons
- Fix RVM, NVM, cmd, README, Adobe Elements and InDesign associations

## 1.6.0
- Fix **Theme Switching requiring to restart the IDE**. Now switching is becoming as easy as pie!
- New feature: **Windows Title Bar Theming**.
    - **Note**: this changes the color of the title bar OS-wide, meaning that even after closing the IDE the color will still be set. (see https://www.howtogeek.com/222831/how-to-get-colored-window-title-bars-on-windows-10-instead-of-white/) for setting it.
- Added more other file icons: Atom, Appveyor, Bean, Cabal, Compass, Doxygen, Dylib, Ghostscript, Github, Jquery, JS Minified, Makefile, Manpage, Nib, Godot, Openoffice, Patch, Phalcon, Redme
- Fix the inactive selection background color in trees
- Fix Status Bar Indicator height in HiDPi screens
- Remove buggy Tool Window Header patch

## 1.5.4
- Change color of unfocused tree elements
- New file icons for: Autohotkey, API Blueprint, MACOSX, Jekyll, Mathematica, ReasonML, RestructuredText, Maven, ObjectiveC, Spring, Tomcat, Test Ruby and VHDL.
- Try to fix issue with createResolvedStylesheet by loading the Darcula default properties

## 1.5.3
- Custom Notification Colors for the Custom Themes
- Change default colors of Notifications to Contrast Color
- Revert the buggy fix of the Tool Window Header (also known as the ToolWindowHeader hack) and add a new action "Patch Tool Window Header" to approve the hack of the Window Header (https://github.com/ChrisRM/material-theme-jetbrains/issues/548)

## 1.5.2.2
- Better Buttons (closes [#561](https://github.com/ChrisRM/material-theme-jetbrains/issues/561) and [#562](https://github.com/ChrisRM/material-theme-jetbrains/issues/562))

## 1.5.2
- Add background theming for IdeFrame

## 1.5.1.2
- Fix Android studio errors

## 1.5.1
- Add expand and collapse icons in ExpandableTexts
- Add Closed icon folder variants for the selected theme 

## 1.5.0
- Improved Monokai Theme by inpirting from Monokai Pro
- Material Monokai Pro color scheme
- New icons for switching themes
- Theme branches and tags from the VCS Log window
- Fix issues with invalid stylesheets and border thickness, causing the settings to not load

## 1.4.7
- Fix issue with out of bounds settings (https://github.com/ChrisRM/material-theme-jetbrains/issues/552)

## 1.4.6
- Setting for changing font to Roboto and bigger font in project view
- Actions for switching arrow style
- Fix Dark Title bar setting

## 1.4.5
- Added more UI Icons
- Fix issue with arrows

## 1.4.4
- Add new option to select the style of arrows in trees
- Restore Bold directories
- Fix https://github.com/ChrisRM/material-theme-jetbrains/issues/540 by setting a lighter selection background color on refactor

## 1.4.3
- Add setting for Dark title bar
- Set bigger font in Project View when not using Compact Sidebar or when Sidebar items height > 28

## 1.4.2.2
- Fix Install Material Wallpaper action
- Fix Custom Material Theme name

## 1.4.2.1
- Remove ToolWindowHeader active background color

## 1.4.1.1
- Fix https://github.com/ChrisRM/material-theme-jetbrains/issues/493

## 1.4.1
- Replace close button

## 1.4.0
- Fix issue with Android Studio loading
- Typescript and Javascript decorators addition (for latest Webstorm only)
- Add some more icons

## 1.3.9
- Better File Status Colors by making use of Darcula/IntelliJ color scheme instead like VCS file colors

## 1.3.8
- Use experimental feature to set dark title bar when using dark themes. Only works on latest EAP, but is planned for 2018.X

## 1.3.7
- Fix issue with Android Studio

## 1.3.6
- Remove Custom Wallpaper feature and instead use an action

## 1.3.5
- Fix Search Everywhere in Windows 10

## 1.3.4
- Fix combobox action style because of latest EAP

## 1.3.3
- New action to install **Material File Colors** on demand instead of automatically at start

## 1.3.2
- Fix Custom themes caret color
- Fix Custom themes TabbedPane color
- Fix Groovy and Scala comment color scheme
- Feature: Compact Table Cells 
- Shut up error at Android Studio start (this disables the File Icons feature)
- Allow setting custom tree indent between 0 and 10
- Allow setting custom line height between 18 and 36
- Fix: Bold directories will not set bold tabs
- Fix issue with color schemes not being persisted

## 1.3.1
- Fix and implement progress bars for older IDEs
- Fix Markdown Navigator color scheme
- Fix color scheme changing at start
- Deprecating EAP :/

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
