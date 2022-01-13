package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.contract.SelftestResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiskStorageSelfTest extends SelfTestBase{
    private static final int REQUIRED_DISK_PERCENTAGE = 5;

    public DiskStorageSelfTest() {
        name = "DiskStorageSelfTest";
        description = "Checks if there is >= 5% disk space left";
    }

    @Override
    public SelftestResult execute() {
        List<String> errors = new ArrayList<>();
        SelftestResult selftestResult = new SelftestResult(name, description, false, errors);

        if(getUsableDiscSpacePercentage() >= REQUIRED_DISK_PERCENTAGE) selftestResult.setPassed(true);
        else {
            selftestResult.setPassed(false);
            errors.add(String.format("Not enough disk space, required %d percent, got %x percent", REQUIRED_DISK_PERCENTAGE, getUsableDiscSpacePercentage()));
        }

        return selftestResult;
    }

    public long getUsableDiscSpacePercentage(){
        File file = new File("d:");
        long totalSpace = file.getTotalSpace();
        long usableSpace = file.getUsableSpace();
        return (usableSpace / totalSpace) * 100;
    }
}
