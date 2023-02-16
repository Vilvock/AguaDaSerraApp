package com.aguadaserra.app.main_ui.fragment.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aguadaserra.app.R
import com.aguadaserra.app.main_ui.adapter.ViewPagerFragmentAdapter
import kotlinx.android.synthetic.main.fragment_multiple_intro_container.*

/**
 * A simple [Fragment] subclass.
 */
class MultipleIntroContainerFragment : Fragment() {


    private val fragmentList = ArrayList<Fragment>()
    private var viewPagerFragmentAdapter: ViewPagerFragmentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiple_intro_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentList.add(
            IntroType1Fragment("Seja Bem-vindo!", "Nas próximas telas apresentaremos\n" +
                "as principais funcionalidades do\n" +
                "aplicativo e como você poderá ter\n" +
                "uma experiência incrível.", 0)
        )

        fragmentList.add(
            IntroType1Fragment("Atualize o seu Perfil", "Adicione uma foto para o seu\n" +
                "perfil e atualize suas informações.\n\n" +
                "Dessa forma, outras pessoas\n" +
                "poderão saber mais sobre você.", 1)
        )

        fragmentList.add(
            IntroType1Fragment("Ajuste suas Preferências", "Aqui você determina quais serão os\n" +
                "critérios de pesquisa para se\n" +
                "conectar com outras pessoas e\n" +
                "permitirá encontros mais\n" +
                "interessantes.", 2)
        )



        viewPagerFragmentAdapter = ViewPagerFragmentAdapter(childFragmentManager, lifecycle, fragmentList)
        viewPager.adapter = viewPagerFragmentAdapter

        indicator.setViewPager(viewPager)
    }

}