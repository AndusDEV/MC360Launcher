# MC360Launcher
This Java program acts as a convenient launcher for Minecraft: Xbox 360 Edition when using the Xenia emulator. It's useful for integrating with Minecraft launchers like Prism Launcher, allowing you to launch the game from them.

## Usage
You can run MC360Launcher using Java. The basic syntax is:
```bash
java -jar MC360Launcher.jar <path-to-xenia> <path-to-mc-rom> [flags]
```
- `<path-to-xenia>`: The full path to your Xenia executable (e.g., C:\Xenia\xenia.exe or /home/user/xenia/xenia).
- `<path-to-mc-rom>`: The full path to your Minecraft: Xbox 360 Edition ROM (e.g., C:\Xenia\content\49AAD81B9F... or /home/user/xenia/content/49AAD81B9F...).

### Linux Flags - [flags]
When running Xenia (the Windows version) on Linux, you can use the following optional flags:
- **--wine**: This flag tells the launcher to use Wine to run Xenia.
- **--proton**: (Recommended) This flag tells the launcher to use Proton - Experimental (from Steam) to run Xenia. This is recommended for better compatibility and performance.
> [!IMPORTANT]
> The launcher expects Proton - Experimental to be located at the default Steam install location: ~/.steam/steam/steamapps/common/Proton - Experimental/proton. If your Proton installation is in a different location, you need to adjust the protonPath variable in the source code or create a symbolic link (probably easier).

> [!NOTE]
> When using --proton, the launcher will create a dedicated Proton prefix directory named proton_prefix within the directory where your Xenia executable is located. This helps isolate Xenia's Wine environment.

## Examples
### Windows
To launch Xenia with Minecraft: Xbox 360 Edition on Windows:
```bash
java -jar MC360Launcher.jar "C:\Xenia\xenia.exe" "C:\Xenia\content\49AAD81B9F..."
```

### Linux (Xenia with Proton - Recommended)
To launch the Windows version of Xenia using Proton - Experimental:
```bash
java -jar MC360Launcher.jar "/home/user/games/xenia/xenia.exe" "/home/user/games/xenia/content/49AAD81B9F..." --proton
```

### Linux (Native Xenia)
If you're using a native Linux build of Xenia:
```bash
java -jar MC360Launcher.jar "/home/user/games/xenia/xenia" "/home/user/games/xenia/content/49AAD81B9F..."
```

### Linux (Xenia with Wine)
To launch the Windows version of Xenia using Wine:
```bash
java -jar MC360Launcher.jar "/home/user/games/xenia/xenia.exe" "/home/user/games/xenia/content/49AAD81B9F..." --wine
```
