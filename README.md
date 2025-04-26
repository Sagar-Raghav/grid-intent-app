Project Name: GridIntentApp

Purpose & Core Functionality
GridIntentApp is an Android application that demonstrates a simple, customizable grid of system intents (browser, dialer, maps, camera, email, SMS, settings, clock, calculator, YouTube, calendar) alongside a dark-mode toggle and a custom splash screen. It serves both as a reference for using implicit intents and as a learning scaffold for Material theming, custom animations, and resource-driven UI.

Key Features
Splash Screen

Full-screen background image (loading_bg) with a horizontal progress bar and percentage text.

Simulated loading from 0→100% before automatically launching the main UI.

Custom drawable (progress_cylindrical.xml) drives the progress bar visuals.

Dark-Mode Support

A Switch in the toolbar area toggles between light and dark themes at runtime.

Uses AppCompatDelegate and DayNight MaterialComponents theme to persist user preference in SharedPreferences.

Grid of Intents

An 11-item GridView populated by a CustomAdapter, each cell built from item_grid.xml (a MaterialCardView containing an icon + label).

Clicking a cell fires off the appropriate implicit or explicit intent (e.g., open browser, dial a number, launch Google Maps, start camera capture, etc.).

Smooth “fade-and-scale” entry animation for each grid item (fade_in_scale.xml) plus an optional click-scale effect (click_scale.xml).

Material Theming & Styling

Theme.GridIntentApp inherits from Theme.MaterialComponents.DayNight.DarkActionBar for consistent Material look-and-feel.

Centralized widget styles in themes.xml for cards (Widget.GridIntentApp.CardView) and progress bars (Widget.GridIntentApp.ProgressBar).

Semantic color attributes (colorPrimary, colorSecondary, colorSurfaceVariant, etc.) defined in colors.xml and applied throughout.

Dimensional resources in dimens.xml and text in strings.xml for full resource-driven adaptability (localization, device scaling).

Resource Organization

Layouts:

activity_splash.xml (FrameLayout + centered LinearLayout)

activity_main.xml (LinearLayout → MaterialToolbar, toggle row, GridView)

item_grid.xml (MaterialCardView wrapper for each grid cell)

Drawables & Animations:

progress_cylindrical.xml (layer-list for horizontal ProgressBar)

fade_in_scale.xml & click_scale.xml for view animations

Manifest:

Declares SplashActivity as the LAUNCHER, MainActivity as exported activities, and references backup/data-extraction rules.
