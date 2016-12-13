; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{C53DFF99-CB5D-4E5A-94CB-824C8B71C753}
AppName=JTill Server
AppVersion=1.0
;AppVerName=JTillServer 1.0
AppPublisher=David Grant
AppPublisherURL=http://davidscode.ddns.net
AppSupportURL=http://davidscode.ddns.net
AppUpdatesURL=http://davidscode.ddns.net
DefaultDirName=C:\JTill Server
DefaultGroupName=JTill Server
OutputDir=C:\Users\David\Honors Project\Till\TillServer\Compiler
OutputBaseFilename=jtillserverinstaller
SetupIconFile=C:\Users\David\Honors Project\Till\TillServer\favicon.ico
Compression=lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "C:\Users\David\Honors Project\Till\TillServer\installer\start.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\David\Honors Project\Till\TillServer\installer\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\TillServer"; Filename: "{app}\start.bat"
Name: "{group}\{cm:ProgramOnTheWeb,TillServer}"; Filename: "http://davidscode.ddns.net"
Name: "{group}\{cm:UninstallProgram,TillServer}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\JTill Server"; Filename: "{app}\start.bat"; IconFilename: "{app}\favicon.ico"; Tasks: desktopicon

[Run]
Filename: "{app}\start.bat"; Description: "{cm:LaunchProgram,TillServer}"; Flags: shellexec postinstall skipifsilent

