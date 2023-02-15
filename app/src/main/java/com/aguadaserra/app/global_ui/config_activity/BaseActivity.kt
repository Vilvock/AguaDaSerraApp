package com.aguadaserra.app.global_ui.config_activity

enum class ToolbarTint {
    WHITE, DARK, NONE
}

enum class SystemBarColor {
    WHITE, DARK
}

interface BaseActivity {
    fun updateSystemBarColor(tint: SystemBarColor)
    fun updatebottomNavigationVisibility(hasBottomNavigation: Boolean)
    fun updateToolbarVisibility(hasToolbar: Boolean)
    fun updateToolbarTitle(title: String)
    fun updateToolbar(tint: ToolbarTint, hasBackButton: Boolean, hasShadow: Boolean)
}