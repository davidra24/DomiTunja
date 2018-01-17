package dmt.appsolution.co.dmt.fragments


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.activities.MenuActivity
import dmt.appsolution.co.dmt.constants.Constants
import kotlinx.android.synthetic.main.fragment_more.*

class MoreFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_more, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startButton()
    }

    private fun startButton(){
        buttonFacebook.setOnClickListener(View.OnClickListener {

            val intent = openFacebook(this.context)
            startActivity(intent)
        })

        buttonTwitter.setOnClickListener(View.OnClickListener {

            val intent = openTwitter(this.context)
            startActivity(intent)
        })

    }

    private fun openFacebook(context:Context): Intent {
        var uri = Uri.parse(Constants.FACEBOOK_URL)
        var packManager = context.packageManager
        try {
            val applicationInfo =  packManager.getPackageInfo("com.facebook.katana", 0)
            if (applicationInfo.versionCode >= 3002850) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + Constants.FACEBOOK_URL)
            }
        } catch (ignored: PackageManager.NameNotFoundException) {
        }

        return Intent(Intent.ACTION_VIEW, uri)
    }

    private fun openTwitter(context:Context):Intent{
        try {
            context.packageManager.getPackageInfo("com.twitter.android", 0)
            return Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=519786756"))
        } catch (e: Exception) {
            return Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/appsolutionco"))
        }

    }


}
