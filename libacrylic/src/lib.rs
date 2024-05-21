extern crate jni;
extern crate libloading;

use std::os::raw::c_void;

use jni::JNIEnv;
use jni::objects::{JClass, JObject, JValue};
use jni::sys::{jboolean, jint, jlong, JNI_FALSE, JNI_TRUE};
use libloading::{Library, Symbol};
use floem_window_vibrancy::{apply_mica, NSVisualEffectMaterial};
use floem_window_vibrancy::apply_tabbed;
use floem_window_vibrancy::apply_acrylic;
use floem_window_vibrancy::apply_vibrancy;

use raw_window_handle::HasRawWindowHandle;
use windows_sys::Win32::Graphics::Dwm::DwmSetWindowAttribute;

#[repr(C)]
struct AccentPolicy {
    n_accent_state: i32,
    n_flags: i32,
    n_color: i32,
    n_animation_id: i32,
}

#[repr(C)]
struct WinCompAttrData {
    n_attribute: i32,
    p_data: *mut AccentPolicy,
    ul_data_size: u32,
}

// Convert the HWND to a raw pointer for the `window-vibrancy` crate
pub struct LongHandle<'a> {
    hwnd: &'a isize,
}

unsafe impl <'a> HasRawWindowHandle for LongHandle<'a> {
    fn raw_window_handle(&self) -> raw_window_handle::RawWindowHandle {                    
        let mut window_handle = raw_window_handle::Win32WindowHandle::empty();
        window_handle.hwnd = (*self.hwnd) as _;
        let window_handle = raw_window_handle::RawWindowHandle::Win32(window_handle);

        window_handle
    }
}

#[no_mangle]
pub extern "system" fn Java_dev_ultreon_mods_micacraft_Acrylic_applyMica(
    env: JNIEnv,
    _class: JClass,
    h_wnd: jlong
) -> jboolean {
    let h_wnd = h_wnd as isize;

    unsafe {
        let window_handle = LongHandle { hwnd: &h_wnd };
        
        let result = apply_mica(window_handle, Some(true));
        if result.is_err() {
            return JNI_FALSE;
        } else {
            return JNI_TRUE;
        }
    }
}

#[no_mangle]
pub extern "system" fn Java_dev_ultreon_mods_micacraft_Acrylic_applyTabbed(
    env: JNIEnv,
    _class: JClass,
    h_wnd: jlong
) -> jboolean {
    let h_wnd = h_wnd as isize;

    unsafe {
        let window_handle = LongHandle { hwnd: &h_wnd };
        
        let result = apply_tabbed(window_handle, Some(true));
        if result.is_err() {
            return JNI_FALSE;
        } else {
            return JNI_TRUE;
        }
    }
}

#[no_mangle]
pub extern "system" fn Java_dev_ultreon_mods_micacraft_Acrylic_applyAcrylic(
    env: JNIEnv,
    _class: JClass,
    h_wnd: jlong
) -> jboolean {
    let h_wnd = h_wnd as isize;

    unsafe {
        let window_handle = LongHandle { hwnd: &h_wnd };
        
        let result = apply_acrylic(window_handle, Some((0, 0, 0, 64)));
        if result.is_err() {
            return JNI_FALSE;
        } else {
            return JNI_TRUE;
        }
    }
}

#[no_mangle]
pub extern "system" fn Java_dev_ultreon_mods_micacraft_Acrylic_applyVibrancy(
    env: JNIEnv,
    _class: JClass,
    h_wnd: jlong
) -> jboolean {
    let h_wnd = h_wnd as isize;

    unsafe {
        let window_handle = LongHandle { hwnd: &h_wnd };
        
        let result = apply_vibrancy(window_handle, NSVisualEffectMaterial::HudWindow, None, None);
        if result.is_err() {
            return JNI_FALSE;
        } else {
            return JNI_TRUE;
        }
    }
}

#[no_mangle]
pub extern "system" fn Java_dev_ultreon_mods_micacraft_Acrylic_dwmSetWindowAttributes(
    env: JNIEnv,
    _class: JClass,
    h_wnd: jlong,
    dw_attr: jint,
    pv_attr: jint,
    cb_attr: jint
) -> jboolean {
    let h_wnd = h_wnd as isize;

    let window_handle = LongHandle { hwnd: &h_wnd };

    unsafe {
        let result = DwmSetWindowAttribute(h_wnd as _, dw_attr as _, pv_attr as u32 as *const c_void, cb_attr as _);
        
        if result != 0 {
            return JNI_FALSE;
        } else {
            return JNI_TRUE;
        }
    }
}
