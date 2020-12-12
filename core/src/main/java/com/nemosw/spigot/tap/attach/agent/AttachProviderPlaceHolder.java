package com.nemosw.spigot.tap.attach.agent;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.sun.tools.attach.spi.AttachProvider;

import java.util.List;

public class AttachProviderPlaceHolder extends AttachProvider
{

    @Override
    public VirtualMachine attachVirtualMachine(String id)
    {
        return null;
    }

    @Override
    public List<VirtualMachineDescriptor> listVirtualMachines()
    {
        return null;
    }

    @Override
    public String name()
    {
        return null;
    }

    @Override
    public String type()
    {
        return null;
    }

}
