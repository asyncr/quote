package com.example.quote.ui.recycler

/*
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private var drawer_layout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        navigationAnimation(binding)
    }

    private fun navigationAnimation(binding: ActivityMainBinding) {
        with(binding) {
            drawer_layout = drawerLayout
            id.host_fragment.let {
                navController = Navigation.findNavController(this@MainActivity, it)
            }
            toolbar.setNavigationOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
            navigationView.setupWithNavController(navController)
            navigationView.setNavigationItemSelectedListener { item ->
                var (idI, title) = item.itemId to item.title.toString()
                when (idI) {
                    id.nav_home -> replacementFragment(FragmentQuoteRandom())
                    id.nav_add -> replacementFragment(QuoteAddFragment())
                    id.nav_filter -> replacementFragment(QuoteListFragment())
                    id.nav_del -> replacementFragment(QuoteDeleteFragment())
                }
                binding.toolbar.title = title
                true
            }
            navigationView.isSelected
            setupDrawerAnimation(this)
        }
    }

    private fun setupDrawerAnimation(binding: ActivityMainBinding) {
        drawer_layout?.setScrimColor(Color.TRANSPARENT)
        drawer_layout?.elevation ?: 0f
        drawer_layout?.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val slideX = drawerView.width * slideOffset
                binding.container.apply {
                    translationX = slideX
                    scaleY = 1 - (slideOffset / 10)
                    val icon = when {
                        slideOffset > 0.5 -> getDrawable(R.drawable.ic_arrow)
                        else -> getDrawable(R.drawable.ic_menu)
                    }
                    binding.toolbar.navigationIcon = icon
                }
            }

            override fun onDrawerStateChanged(newState: Int) {}
            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerOpened(drawerView: View) {}
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        drawer_layout?.closeDrawer(GravityCompat.START)
    }

    override fun onSupportNavigateUp() = NavigationUI.navigateUp(navController, drawer_layout)

    private fun replacementFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(id.host_fragment, fragment).commit()
        drawer_layout?.closeDrawer(GravityCompat.START)
    }

}
*/