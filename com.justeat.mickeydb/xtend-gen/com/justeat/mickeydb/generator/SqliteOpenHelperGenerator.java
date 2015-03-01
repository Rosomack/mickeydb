package com.justeat.mickeydb.generator;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.justeat.mickeydb.MickeyDatabaseModel;
import com.justeat.mickeydb.Strings;
import com.justeat.mickeydb.generator.SqliteDatabaseSnapshot;
import com.justeat.mickeydb.generator.SqliteDatabaseStatementGenerator;
import com.justeat.mickeydb.mickeyLang.CreateTableStatement;
import com.justeat.mickeydb.mickeyLang.CreateViewStatement;
import com.justeat.mickeydb.mickeyLang.DDLStatement;
import com.justeat.mickeydb.mickeyLang.MigrationBlock;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class SqliteOpenHelperGenerator {
  @Inject
  @Extension
  private SqliteDatabaseStatementGenerator _sqliteDatabaseStatementGenerator;
  
  public CharSequence generate(final MickeyDatabaseModel model) {
    StringConcatenation _builder = new StringConcatenation();
    SqliteDatabaseSnapshot snapshot = model.getSnapshot();
    _builder.newLineIfNotEmpty();
    _builder.append("/*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Generated by Robotoworks Mechanoid");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("package ");
    String _packageName = model.getPackageName();
    _builder.append(_packageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import android.content.Context;");
    _builder.newLine();
    _builder.append("import android.database.sqlite.SQLiteDatabase;");
    _builder.newLine();
    _builder.append("import com.robotoworks.mechanoid.db.MechanoidSQLiteOpenHelper;");
    _builder.newLine();
    _builder.append("import com.robotoworks.mechanoid.db.SQLiteMigration;");
    _builder.newLine();
    _builder.newLine();
    {
      int _size = model.migrations.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        {
          for(final MigrationBlock migration : model.migrations) {
            _builder.append("import ");
            String _packageName_1 = model.getPackageName();
            _builder.append(_packageName_1, "");
            _builder.append(".migrations.Default");
            String _databaseName = model.getDatabaseName();
            String _pascalize = Strings.pascalize(_databaseName);
            _builder.append(_pascalize, "");
            _builder.append("MigrationV");
            BigDecimal _version = migration.getVersion();
            _builder.append(_version, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    _builder.append("public abstract class Abstract");
    String _databaseName_1 = model.getDatabaseName();
    String _pascalize_1 = Strings.pascalize(_databaseName_1);
    _builder.append(_pascalize_1, "");
    _builder.append("OpenHelper extends MechanoidSQLiteOpenHelper {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private static final String DATABASE_NAME = \"");
    String _databaseName_2 = model.getDatabaseName();
    _builder.append(_databaseName_2, "\t");
    _builder.append(".db\";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static final int VERSION = ");
    MigrationBlock _last = IterableExtensions.<MigrationBlock>last(model.migrations);
    BigDecimal _version_1 = null;
    if (_last!=null) {
      _version_1=_last.getVersion();
    }
    _builder.append(_version_1, "\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public interface Sources {");
    _builder.newLine();
    {
      ArrayList<CreateTableStatement> _tables = snapshot.getTables();
      for(final CreateTableStatement table : _tables) {
        _builder.append("\t\t");
        _builder.append("String ");
        String _name = table.getName();
        String _underscore = Strings.underscore(_name);
        String _upperCase = _underscore.toUpperCase();
        _builder.append(_upperCase, "\t\t");
        _builder.append(" = \"");
        String _name_1 = table.getName();
        _builder.append(_name_1, "\t\t");
        _builder.append("\";");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      ArrayList<CreateViewStatement> _views = snapshot.getViews();
      for(final CreateViewStatement view : _views) {
        _builder.append("\t\t");
        _builder.append("String ");
        String _name_2 = view.getName();
        String _underscore_1 = Strings.underscore(_name_2);
        String _upperCase_1 = _underscore_1.toUpperCase();
        _builder.append(_upperCase_1, "\t\t");
        _builder.append(" = \"");
        String _name_3 = view.getName();
        _builder.append(_name_3, "\t\t");
        _builder.append("\";");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      for(final CreateTableStatement table_1 : model.initTables) {
        _builder.append("\t\t");
        _builder.append("String ");
        String _name_4 = table_1.getName();
        String _underscore_2 = Strings.underscore(_name_4);
        String _upperCase_2 = _underscore_2.toUpperCase();
        _builder.append(_upperCase_2, "\t\t");
        _builder.append(" = \"");
        String _name_5 = table_1.getName();
        _builder.append(_name_5, "\t\t");
        _builder.append("\";");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      for(final CreateViewStatement view_1 : model.initViews) {
        _builder.append("\t\t");
        _builder.append("String ");
        String _name_6 = view_1.getName();
        String _underscore_3 = Strings.underscore(_name_6);
        String _upperCase_3 = _underscore_3.toUpperCase();
        _builder.append(_upperCase_3, "\t\t");
        _builder.append(" = \"");
        String _name_7 = view_1.getName();
        _builder.append(_name_7, "\t\t");
        _builder.append("\";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Abstract");
    String _databaseName_3 = model.getDatabaseName();
    String _pascalize_2 = Strings.pascalize(_databaseName_3);
    _builder.append(_pascalize_2, "\t");
    _builder.append("OpenHelper(Context context) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(context, DATABASE_NAME, null, VERSION);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Abstract");
    String _databaseName_4 = model.getDatabaseName();
    String _pascalize_3 = Strings.pascalize(_databaseName_4);
    _builder.append(_pascalize_3, "\t");
    _builder.append("OpenHelper(Context context, String name) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(context, name, null, VERSION);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void onCreate(SQLiteDatabase db) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("applyMigrations(db, 0, VERSION);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      boolean _or = false;
      boolean _isEmpty = model.initTables.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _or = true;
      } else {
        boolean _isEmpty_1 = model.initViews.isEmpty();
        boolean _not_1 = (!_isEmpty_1);
        _or = _not_1;
      }
      if (_or) {
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void onOpen(SQLiteDatabase db) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("super.onOpen(db);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        Iterable<DDLStatement> _filter = Iterables.<DDLStatement>filter(model.initTables, DDLStatement.class);
        CharSequence _generateStatements = this._sqliteDatabaseStatementGenerator.generateStatements(_filter);
        _builder.append(_generateStatements, "\t\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        Iterable<DDLStatement> _filter_1 = Iterables.<DDLStatement>filter(model.initViews, DDLStatement.class);
        CharSequence _generateStatements_1 = this._sqliteDatabaseStatementGenerator.generateStatements(_filter_1);
        _builder.append(_generateStatements_1, "\t\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected SQLiteMigration createMigration(int version) {");
    _builder.newLine();
    {
      int _size_1 = model.migrations.size();
      boolean _greaterThan_1 = (_size_1 > 0);
      if (_greaterThan_1) {
        _builder.append("\t\t");
        int version = (-1);
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("switch(version) {");
        _builder.newLine();
        {
          for(final MigrationBlock migration_1 : model.migrations) {
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("case ");
            _builder.append(version = (version + 1), "\t\t\t");
            _builder.append(":");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("return create");
            String _databaseName_5 = model.getDatabaseName();
            String _pascalize_4 = Strings.pascalize(_databaseName_5);
            _builder.append(_pascalize_4, "\t\t\t\t");
            _builder.append("MigrationV");
            BigDecimal _version_2 = migration_1.getVersion();
            _builder.append(_version_2, "\t\t\t\t");
            _builder.append("();");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("default:");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t\t");
        _builder.append("throw new IllegalStateException(\"No migration for version \" + version);");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
      } else {
        _builder.append("\t\t");
        _builder.append("throw new IllegalStateException(\"No migrations for any version\");");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      int _size_2 = model.migrations.size();
      boolean _greaterThan_2 = (_size_2 > 0);
      if (_greaterThan_2) {
        {
          for(final MigrationBlock migration_2 : model.migrations) {
            _builder.append("\t");
            _builder.append("protected SQLiteMigration create");
            String _databaseName_6 = model.getDatabaseName();
            String _pascalize_5 = Strings.pascalize(_databaseName_6);
            _builder.append(_pascalize_5, "\t");
            _builder.append("MigrationV");
            BigDecimal _version_3 = migration_2.getVersion();
            _builder.append(_version_3, "\t");
            _builder.append("() {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("return new Default");
            String _databaseName_7 = model.getDatabaseName();
            String _pascalize_6 = Strings.pascalize(_databaseName_7);
            _builder.append(_pascalize_6, "\t\t");
            _builder.append("MigrationV");
            BigDecimal _version_4 = migration_2.getVersion();
            _builder.append(_version_4, "\t\t");
            _builder.append("();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateStub(final MickeyDatabaseModel model, final SqliteDatabaseSnapshot snapshot) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Generated by Robotoworks Mechanoid");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("package ");
    String _packageName = model.getPackageName();
    _builder.append(_packageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import android.content.Context;");
    _builder.newLine();
    _builder.append("import ");
    String _packageName_1 = model.getPackageName();
    _builder.append(_packageName_1, "");
    _builder.append(".Abstract");
    String _databaseName = model.getDatabaseName();
    String _pascalize = Strings.pascalize(_databaseName);
    _builder.append(_pascalize, "");
    _builder.append("OpenHelper;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    String _databaseName_1 = model.getDatabaseName();
    String _pascalize_1 = Strings.pascalize(_databaseName_1);
    _builder.append(_pascalize_1, "");
    _builder.append("OpenHelper extends Abstract");
    String _databaseName_2 = model.getDatabaseName();
    String _pascalize_2 = Strings.pascalize(_databaseName_2);
    _builder.append(_pascalize_2, "");
    _builder.append("OpenHelper {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("public ");
    String _databaseName_3 = model.getDatabaseName();
    String _pascalize_3 = Strings.pascalize(_databaseName_3);
    _builder.append(_pascalize_3, "\t");
    _builder.append("OpenHelper(Context context) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(context);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
