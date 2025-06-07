import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MC360Launcher {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java -jar MC360Launcher.jar <path-to-xenia> <path-to-mc> [flags]");
            System.exit(1);
        }

        String xeniaPath = args[0];
        String romPath = args[1];

        boolean useWine = false;
        boolean useProton = false;

        // Optional Linux flags (when running Xenia for Windows not native)
        for (int i = 2; i < args.length; i++) {
            String arg = args[i];
            if (arg.equalsIgnoreCase("--wine")) { // Untested
                useWine = true;
            } else if (arg.equalsIgnoreCase("--proton")) { // Recommended
                useWine = true;
                useProton = true;
            }
        }

        // Files check (Xenia exec & Minecraft ROM)
        File xeniaFile = new File(xeniaPath);
        File romFile = new File(romPath);

        if (!xeniaFile.exists()) {
            System.err.println("Error: Xenia executable not found at " + xeniaPath);
            System.exit(2);
        }

        if (!romFile.exists()) {
            System.err.println("Error: Minecraft ROM not found at " + romPath);
            System.exit(2);
        }

        // Command to run Xenia (based on flags)
        List<String> command = new ArrayList<>();
        ProcessBuilder builder;

        if (useWine) {
            if (useProton) {
                String protonPath = System.getProperty("user.home") + "/.steam/steam/steamapps/common/Proton - Experimental/proton";
                File proton = new File(protonPath);
                if (!proton.exists()) {
                    System.err.println("Error: Proton Experimental not found at " + protonPath);
                    System.exit(4);
                }

                command.add(protonPath);
                command.add("run");
            } else {
                command.add("wine");
            }
        }

        command.add(xeniaPath);
        command.add(romPath);

        builder = new ProcessBuilder(command);
        builder.directory(xeniaFile.getParentFile());

        // Proton prefix path
        if (useProton) {
        File prefixDir = new File(xeniaFile.getParentFile(), "proton_prefix");
            if (!prefixDir.exists()) {
                if (!prefixDir.mkdirs()) {
                    System.err.println("Error: Failed to create Proton prefix directory at " + prefixDir.getAbsolutePath());
                    System.exit(5);
                }
            }

            // Required proton env vars
            builder.environment().put("STEAM_COMPAT_DATA_PATH", prefixDir.getAbsolutePath());
            builder.environment().put("STEAM_COMPAT_CLIENT_INSTALL_PATH", System.getProperty("user.home") + "/.steam/steam");
            builder.environment().put("STEAM_COMPAT_TOOL_PATHS", System.getProperty("user.home") + "/.steam/steam/steamapps/common");
        }

        // Try to run Xenia
        System.out.println("Running: " + String.join(" ", command));
        try {
            builder.inheritIO().start();
        } catch (IOException e) {
            System.err.println("Failed to launch Xenia:");
            e.printStackTrace();
            System.exit(3);
        }
    }
}
