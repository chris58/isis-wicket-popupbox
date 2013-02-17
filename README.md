isis-wicket-popupbox
========================

Extention to display a popup window with an external URL

### Configuration

In your project's parent `pom.xml`, add to the `<dependencyManagement>` section:

    <dependencyManagement>
        <dependencies>
            ...
            <dependency>
                <groupId>com.marintek.isis.wicket.ui.components</groupId>
                <artifactId>marintek-isis-wicket-popupbox</artifactId>
                <version>1.0.0-SNAPSHOT</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            ...
        </dependencies>
    </dependencyManagement>

In your project's DOM `pom.xml`, add a dependency on the `applib` module:

    <dependencies>
        ...
        <dependency>
            <groupId>com.marintek.isis.wicket.ui.components</groupId>
            <artifactId>marintek-isis-wicket-popupbox-applib</artifactId>
        </dependency>
        ...
    </dependencies> 


Finally, there is a required dependency in your project's webapp `pom.xml` to the `ui` module:

    <dependencies>
        ...
        <dependency>
            <groupId>com.marintek.isis.wicket.ui.components</groupId>
            <artifactId>marintek-isis-wicket-popupbox-ui</artifactId>
        </dependency>
        ...
    </dependencies> 


### Usage

This projects' applib module defines the `PopupWicketBox` value type. 

Returning an instance of `PopupWicketBox` from any action will unfortunately show a link which you have to click to render the popupbox....


<pre>

public class ToDoItems {

    public PopupWicketBox createPopupBox() {
       return new PopupWicketBox("http://www.marinetraffic.com/ais/default.aspx?mmsi=259139000", "Popup", 600, 600, 200, 200);
    }
   ...

}
</pre>


