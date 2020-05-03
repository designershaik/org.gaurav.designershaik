Changeset:
Branch: idempier_4.1 
Changeset:8 (144a416a8e93) Merge with default

Branch: default
Changeset:7 (294cc7adf700) Added Sysconfig whether to show URL on top of Form or not


If this plugin is already deployed in server with above changeset level
and client moving to latest code then must need to follow below steps

A. Re-Apply 2Pack
	i.  com.logilite.metabaseBI/META-INF/2Pack_1.0.1.zip
	ii. com.logilite.metabaseBI/META-INF/2Pack_1.0.2.zip
	
B. Execute below 4 SQLs for correcting existing data and deleting unwanted columns


Execute SQLs:

1)
UPDATE AD_Form
SET X_MetabaseURL = Zito_MetabaseURL
WHERE Zito_MetabaseURL IS NOT NULL;

2)
UPDATE AD_Form
SET X_DashboardNumber = Zito_DashboardNumber
WHERE Zito_DashboardNumber IS NOT NULL;

3)
ALTER TABLE AD_Form DROP COLUMN Zito_MetabaseURL;

4)
ALTER TABLE AD_Form DROP COLUMN Zito_DashboardNumber;


For reference only: to check column exists in AD_Form table.
> SELECT Column_Name FROM information_schema.columns WHERE Table_Name ILIKE 'AD_Form' AND Column_Name ILIKE 'Zito_*' 
