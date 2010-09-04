/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package managment;


import graphics.PrimeMain;
import objects.Software.base;
import objects.Software.fileSystems;
import objects.softwareObjects.Antivirus;
import objects.softwareObjects.Backup;
import objects.softwareObjects.Database;
import objects.softwareObjects.Email;
import objects.softwareObjects.Firewall;
import objects.softwareObjects.GenericSoftware;
import objects.softwareObjects.NASsoftware;
import objects.softwareObjects.OfficeSuite;
import objects.softwareObjects.OperatingSystem;
import objects.softwareObjects.Proxy;
import objects.softwareObjects.RemoteDesktop;
import objects.softwareObjects.SecuritySuite;
import objects.softwareObjects.VirtualizationSoftware;
import objects.softwareObjects.Webserver;


/**
 * /** In this class the systems standard internal components are made and
 * served to the rest of the system. When any new object is made, the internal
 * components such as motherboard or CPU will be provided by the functions in
 * this class.
 * 
 * @author Bahram Malaekeh
 */
public class MakeStandardSoftware
{
	/**
	 * Creates and returns what the system will regard as a generic software
	 * object.
	 * 
	 * @return A new {@link GenericSoftware}
	 */
	public GenericSoftware getSt_GenSoftware()
	{
		GenericSoftware genSoft = new GenericSoftware(
				PrimeMain.texts.getString("generalSoftwareName"),
				PrimeMain.texts.getString("generalSoftwareDescription"), "1");

		return genSoft;
	}



	/**
	 * Creates and returns what the system will regard as the standard
	 * Antivirus.
	 * 
	 * @return A new antivirus
	 */
	public Antivirus getSt_AV()
	{
		Antivirus av = new Antivirus(
				PrimeMain.texts.getString("standardAntivirusName"),
				PrimeMain.texts.getString("standardAntivirusDescription"), "1");

		return av;
	}



	/**
	 * Creates and returns what the system will regard as the standard Backup
	 * software.
	 * 
	 * @return A new Backup software
	 */
	public Backup getSt_Backup()
	{
		Backup back = new Backup(
				PrimeMain.texts.getString("standardBackupName"),
				PrimeMain.texts.getString("standardBackupDescription"), "1");

		return back;
	}



	/**
	 * Creates and returns what the system will regard as the standard
	 * {@link NASsoftware} software.
	 * 
	 * @return A new {@link NASsoftware} software
	 */
	public NASsoftware getSt_NASSoftware()
	{
		NASsoftware nas = new NASsoftware(
				PrimeMain.texts.getString("standardNASsoftwareName"),
				PrimeMain.texts.getString("standardNASsoftwareDescription"),
				"1");

		return nas;
	}



	/**
	 * Creates and returns what the system will regard as the standard Database.
	 * 
	 * @return A new Database
	 */
	public Database getSt_Database()
	{
		Database db = new Database(
				PrimeMain.texts.getString("standardDatabaseName"),
				PrimeMain.texts.getString("standardDatabaseDescription"), "1");

		return db;
	}



	/**
	 * Creates and returns what the system will regard as the standard
	 * {@link VirtualizationSoftware}.
	 * 
	 * @return A new {@link VirtualizationSoftware}
	 */
	public VirtualizationSoftware getSt_VirtualizationSoftware()
	{
		VirtualizationSoftware virt = new VirtualizationSoftware(
				PrimeMain.texts.getString("standardVirtualizationSoftwareName"),
				PrimeMain.texts
						.getString("standardVirtualizationSoftwareDescription"),
				"1");

		return virt;
	}



	/**
	 * Creates and returns what the system will regard as the standard Email
	 * software. This is software that can read emails.
	 * 
	 * @return A new Email software
	 */
	public Email getSt_Email()
	{
		Email mail = new Email(PrimeMain.texts.getString("standardEmailName"),
				PrimeMain.texts.getString("standardEmailDescription"), "1");

		return mail;
	}



	/**
	 * Creates and returns what the system will regard as the standard Firewall.
	 * 
	 * @return A new Firewall
	 */
	public Firewall getSt_Firewall()
	{
		Firewall fw = new Firewall(
				PrimeMain.texts.getString("standardFirewallName"),
				PrimeMain.texts.getString("standardFirewallDescription"), "1");

		return fw;
	}


	/**
	 * Creates and returns what the system will regard as the standard Office
	 * Suite. This suite has a set of predefined file types it supports. Other
	 * file types can be added by the user.
	 * 
	 * @return A new Office Suite
	 */
	public OfficeSuite getSt_OfficeSuite()
	{
		String[] formats = new String[15];
		formats[0] = ".doc";
		formats[1] = ".txt";
		formats[2] = ".xml";
		formats[3] = ".xls";
		formats[4] = ".ppt";
		formats[5] = ".rtf";


		OfficeSuite offSuite = new OfficeSuite(
				PrimeMain.texts.getString("standardOfficeSuiteName"),
				PrimeMain.texts.getString("standardOfficeSuiteDescription"),
				"1", formats);

		return offSuite;
	}



	/**
	 * Creates and returns what the system will regard as the standard Operating
	 * System. This Operating system will be the software that all software that
	 * is to be installed on the system will be checked against for
	 * compatibility. If the user has added other Operating systems to the
	 * machine, those will also be included in the check for software
	 * compatibility,
	 * 
	 * @return A new Operating System
	 */
	public OperatingSystem getSt_OS()
	{
		OperatingSystem os = new OperatingSystem("Windows XP",
				PrimeMain.texts.getString("standardOSDescription"), "5.1");

		return os;
	}



	/**
	 * Creates and returns what the system will regard as the standard Proxy.
	 * 
	 * @return A new Proxy
	 */
	public Proxy getSt_Proxy()
	{
		Proxy proxy = new Proxy(PrimeMain.texts.getString("standardProxyName"),
				PrimeMain.texts.getString("standardProxyDescription"), "1");

		return proxy;
	}



	/**
	 * Creates and returns what the system will regard as the standard Security
	 * Suite.
	 * 
	 * @return A new Security Suite
	 */
	public SecuritySuite getSt_SecSuite()
	{
		SecuritySuite secSuite = new SecuritySuite(
				PrimeMain.texts.getString("standardSecuritySuiteName"),
				PrimeMain.texts.getString("standardSecuritySuiteDescription"),
				"1", false, false, false);


		return secSuite;
	}



	/**
	 * Creates and returns what the system will regard as the standard
	 * Webserver.
	 * 
	 * @return A new Webserver
	 */
	public Webserver getSt_Webserver()
	{
		Webserver ws = new Webserver(
				PrimeMain.texts.getString("standardWebserverName"),
				PrimeMain.texts.getString("standardWebserverDescription"), "1");

		return ws;
	}




	/**
	 * This function creates and returns a standard set of
	 * {@link OperatingSystem} that will be the standard {@link OperatingSystem}
	 * that this system will use.
	 * 
	 * These will not be able to be changed.
	 */
	public static OperatingSystem[] getSystemStandardOSs()
	{
		OperatingSystem[] OSs = new OperatingSystem[9];

		OSs[0] = new OperatingSystem("Windows 2000",
				PrimeMain.texts.getString("standardWin2000OSDescription"), "5");
		OSs[0].setHasGUI(true);
		OSs[0].setBase(base.WINDOWS);
		fileSystems[] fsWin2000 = { fileSystems.FAT16, fileSystems.FAT32,
				fileSystems.NTFS };
		OSs[0].setFs(fsWin2000);


		OSs[1] = new OperatingSystem("Windows XP",
				PrimeMain.texts.getString("standardWinXPOSDescription"), "5.1");
		OSs[1].setHasGUI(true);
		OSs[1].setBase(base.WINDOWS);
		fileSystems[] fsWinXP = { fileSystems.FAT16, fileSystems.FAT32,
				fileSystems.NTFS, fileSystems.exFAT };
		OSs[1].setFs(fsWinXP);


		OSs[2] = new OperatingSystem("Windows Vista",
				PrimeMain.texts.getString("standardWinVistaOSDescription"), "6");
		OSs[2].setHasGUI(true);
		OSs[2].setBase(base.WINDOWS);
		fileSystems[] fsWinVista = { fileSystems.FAT16, fileSystems.FAT32,
				fileSystems.NTFS, fileSystems.exFAT };
		OSs[2].setFs(fsWinVista);


		OSs[3] = new OperatingSystem("Windows 7",
				PrimeMain.texts.getString("standardWinVistaOSDescription"), "7");
		OSs[3].setHasGUI(true);
		OSs[3].setBase(base.WINDOWS);
		fileSystems[] fsWin7 = { fileSystems.FAT16, fileSystems.FAT32,
				fileSystems.NTFS, fileSystems.exFAT };
		OSs[3].setFs(fsWin7);


		OSs[4] = new OperatingSystem("Ubuntu 9.10",
				PrimeMain.texts
						.getString("standardLinuxUbuntu9.10OSDescription"),
				"9.10");
		OSs[4].setHasGUI(true);
		OSs[4].setBase(base.GNU_LINUX);
		fileSystems[] fsUbuntu910 = { fileSystems.FAT16, fileSystems.FAT32,
				fileSystems.NTFS, fileSystems.EXT2, fileSystems.EXT3,
				fileSystems.EXT4, fileSystems.ReiserFS, fileSystems.JFS,
				fileSystems.XFS };
		OSs[4].setFs(fsUbuntu910);


		OSs[5] = new OperatingSystem("Ubuntu 10.04",
				PrimeMain.texts
						.getString("standardLinuxUbuntu10.04OSDescription"),
				"10.04");
		OSs[5].setHasGUI(true);
		OSs[5].setBase(base.GNU_LINUX);
		fileSystems[] fsUbuntu1004 = { fileSystems.FAT16, fileSystems.FAT32,
				fileSystems.NTFS, fileSystems.EXT2, fileSystems.EXT3,
				fileSystems.EXT4, fileSystems.ReiserFS, fileSystems.JFS,
				fileSystems.XFS };
		OSs[5].setFs(fsUbuntu1004);


		OSs[6] = new OperatingSystem("Debian 5",
				PrimeMain.texts.getString("standardLinuxDebian5OSDescription"),
				"5");
		OSs[6].setHasGUI(true);
		OSs[6].setBase(base.GNU_LINUX);
		fileSystems[] fsDebian5 = { fileSystems.FAT16, fileSystems.FAT32,
				fileSystems.NTFS, fileSystems.EXT2, fileSystems.EXT3,
				fileSystems.EXT4, fileSystems.ReiserFS, fileSystems.JFS,
				fileSystems.XFS };
		OSs[6].setFs(fsDebian5);


		OSs[7] = new OperatingSystem("MacOS X 10.5",
				PrimeMain.texts.getString("standardUnixMac10.5OSDescription"),
				"10.5");
		OSs[7].setHasGUI(true);
		OSs[7].setBase(base.MAC);
		fileSystems[] fsMac105 = { fileSystems.FAT16, fileSystems.FAT32,
				fileSystems.NTFS, fileSystems.HFS, fileSystems.HFSplus };
		OSs[7].setFs(fsMac105);


		OSs[8] = new OperatingSystem("MacOS X 10.6",
				PrimeMain.texts.getString("standardUnixMac10.6OSDescription"),
				"10.6");
		OSs[8].setHasGUI(true);
		OSs[8].setBase(base.MAC);
		fileSystems[] fsMac106 = { fileSystems.FAT16, fileSystems.FAT32,
				fileSystems.NTFS, fileSystems.HFS, fileSystems.HFSplus };
		OSs[8].setFs(fsMac106);


		return OSs;
	}



	/**
	 * Creates and returns what the system will regard as a remote desktop
	 * software object.
	 * 
	 * @return A new {@link RemoteDesktop}
	 */
	public RemoteDesktop getSt_RemoteDesktopSoftware()
	{
		RemoteDesktop genSoft = new RemoteDesktop(
				PrimeMain.texts.getString("standardRemoteDesktopName"),
				PrimeMain.texts.getString("standardRemoteDesktopDescription"),
				"1");

		return genSoft;
	}
}
