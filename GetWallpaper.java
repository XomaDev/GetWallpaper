package com.extension.GetWall.xoma;


import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

//External
import java.io.FileOutputStream;
import android.graphics.Bitmap;
import android.app.WallpaperManager;
import android.graphics.drawable.BitmapDrawable;
import android.content.Context;
import android.graphics.drawable.Drawable;
import java.io.IOException;

@DesignerComponent(version = 1,
        category = ComponentCategory.EXTENSION,
        description = "Save the wallpaper of the home screen, made my Kumaraswamy",
        nonVisible = true,
        iconName = "https://community.appinventor.mit.edu/user_avatar/community.appinventor.mit.edu/kumaraswamy_b.g/32/13770_2.png")

@SimpleObject(external = true)

public class GetWallpaper extends AndroidNonvisibleComponent {

    private ComponentContainer container;

    public GetWallpaper(ComponentContainer container) {
        super(container.$form());
        this.container = container;
    }

    @SimpleFunction(description = "Get WallPapaer")
    public void GetWall(String path) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(container.$context());
            Drawable wallpaperDrawable = wallpaperManager.getDrawable();
            Bitmap bitmap = ((BitmapDrawable)wallpaperDrawable).getBitmap();
            FileOutputStream out = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            SavedWallpaper(path);
        }
        catch(Exception e) {
            SavedWallpaper(String.valueOf(e));
        }
    }

    @SimpleEvent(description = "Triggered when wallpaper is saved into internal storage.")
    public void SavedWallpaper(String path) {
        EventDispatcher.dispatchEvent(this, "SavedWallpaper", path);
    }
}




