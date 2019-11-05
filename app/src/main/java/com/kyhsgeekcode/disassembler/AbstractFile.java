package com.kyhsgeekcode.disassembler;

//represents a raw file and interface

import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.lxtreme.binutils.elf.MachineType;

public abstract class AbstractFile implements Closeable {
    private static final String TAG = "AbstractFile";
    public static AbstractFile getInstance(String tag) {
        File file = new File(tag);
        Log.e(TAG, "Unimplemented method");
        //file을 읽던가 mainactivity의 코드를 잘 가져와서 AbstractFile을 만든다.
        // FacileAPI거만 아니면 파일 객체와 내용만 주면 된다.
        //다시 읽는건 비효율적으로 보일 수 있지만 어쨌든 다시 읽어서 넘겨준다.
        //AfterReadFully() 참고하기!
        //다 읽고
        //AfterReadFully 로직으로 AbstractFile을 만들어 리턴한다.
        //그리고 AfterReadFully 함수는 없어질지도 모른다!
        //그러면 중복코드도 사라짐
        //행복회로
        return null;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    @Override
    public void close() throws IOException {
        return;
    }

    public long getEntryPoint() {
        return entryPoint;
    }

    public long getCodeSectionBase() {
        return codeBase;
    }

    public long getCodeSectionLimit() {
        return codeLimit;
    }

    public long getCodeVirtAddr() {
        return codeVirtualAddress;
    }

    public List<Symbol> getSymbols() {
        if (symbols == null)
            symbols = new ArrayList<>();
        return symbols;
    }

    public List<PLT> getImportSymbols() {
        if (importSymbols == null)
            importSymbols = new ArrayList<>();
        return importSymbols;
    }

    @Override
    public String toString() {
        if (fileContents == null) {
            return "The file has not been configured. You should setup manually in the first page before you can see the details.";
        }
        StringBuilder builder = new StringBuilder(this instanceof RawFile ?
                "The file has not been configured. You should setup manually in the first page before you can see the details."
                        + System.lineSeparator()
                : "");
        builder.append(/*R.getString(R.string.FileSize)*/"File Size:").append(Integer.toHexString(fileContents.length))
                .append(ls);
        builder.append(MainActivity.context.getString(R.string.FoffsCS)).append(Long.toHexString(codeBase))
                .append(ls);
        builder.append(MainActivity.context.getString(R.string.FoffsCSEd)).append(Long.toHexString(codeLimit))
                .append(ls);
        builder.append(MainActivity.context.getString(R.string.FoffsEP)).append(Long.toHexString(codeBase + entryPoint))
                .append(ls);
        builder.append(MainActivity.context.getString(R.string.VAofCS)).append(Long.toHexString(codeVirtualAddress))
                .append(ls);
        builder.append(MainActivity.context.getString(R.string.VAofCSE)).append(Long.toHexString(codeLimit + codeVirtualAddress))
                .append(ls);
        builder.append(MainActivity.context.getString(R.string.VAofEP)).append(Long.toHexString(entryPoint + codeVirtualAddress));
        return builder.toString();
    }

    //	public AbstractFile(File file) throws IOException
//	{
//		
//	}
//	public AbstractFile(FileChannel channel)
//	{
//		
//	}
    String ls = System.lineSeparator();
    long codeBase = 0;
    long codeLimit = 0;
    List<Symbol> symbols;
    List<PLT> importSymbols;
    byte[] fileContents;
    long entryPoint = 0;
    long codeVirtualAddress = 0;
    MachineType machineType;
    String path = "";

    public void Disassemble(MainActivity mainActivity) {
        mainActivity.DisassembleFile(0);
    }
}
