package com.example.myapplication.GeniusDirection;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.badlogic.gdx.utils.XmlWriter;

import javax.microedition.khronos.opengles.GL10;

public class Lib013_XmlReader1 extends ApplicationAdapter{

    //XmlReader reader;
    //Element element;
    BitmapFont font;
    String strShow1, strShow2;
    SpriteBatch batch;
    StringWriter stringWriter;
    String configFileName = "xml/3.xml";
    
    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();
        
        font = new BitmapFont();
        font.setColor( Color.BLACK );
        batch = new SpriteBatch();
        
        Preferences preferences = Gdx.app.getPreferences( "pre1.test" );
        preferences.putString( "name", "Kitty" );
        preferences.putBoolean( "visible", true );
        preferences.putInteger( "age", 25 );
        preferences.flush();
        
        String strName1 = preferences.getString( "name" );
        boolean isVisible = preferences.getBoolean( "visible" );
        int age1 = preferences.getInteger( "age" );        
        strShow1 = strName1 + "  " + isVisible + "  " + age1;
        
        
        
        try{
            XmlReader xmlReader = new XmlReader( );
            Element eleRoot = xmlReader.parse( Gdx.files.internal( "xml/1.xml" ) );    
            Element eleStu = eleRoot.getChildByName( "student" );

            String strName2 = eleStu.get( "name" );
            boolean isMale = eleStu.getBoolean( "male" );
            int age2 = eleStu.getInt( "age" );
            strShow2 = strName2 + "  " + isMale + "  " + age2;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        
        
        try{
            stringWriter = new StringWriter();
            //stringWriter.append( "" );
            XmlWriter xmlWriter = new XmlWriter( stringWriter );
            xmlWriter.element("information")
                        .element( "person" ).attribute( "id", "0201" )
                            .element("name").text("Nacy").pop()
                            .element("hobby").text("basketball").pop()
                            .element("age").text("34").pop()
                        .pop()
                    .pop();
            xmlWriter.close();
            FileHandle file = Gdx.files.local( "set.xml" );
            file.writeString( stringWriter.toString(), false );
            System.out.println( stringWriter.toString() );
        }
        catch(IOException e){
            e.printStackTrace();
        }
    
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );
        
        batch.begin();
        font.draw( batch, strShow1, 100, 150 );
        font.draw( batch, strShow2, 100, 100 );
        //font.drawMultiLine( batch, stringWriter.toString(), 100, 300 );
        font.draw( batch, stringWriter.toString(), 100, 300 );
        batch.end();
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
    }

}