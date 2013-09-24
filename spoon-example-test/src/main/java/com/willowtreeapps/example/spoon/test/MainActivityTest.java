package com.willowtreeapps.example.spoon.test;

// User: jnye
// Date: 9/17/13
// Time: 4:13 PM
// Copyright (c) 2013 WillowTree Apps, Inc. All rights reserved.

import com.jayway.android.robotium.solo.Solo;
import com.squareup.spoon.Spoon;
import com.willowtreeapps.example.spoon.app.MainActivity;
import com.willowtreeapps.example.spoon.app.OtherActivity;
import com.willowtreeapps.example.spoon.app.R;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;

import static org.fest.assertions.api.ANDROID.assertThat;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;
    private Solo mSolo;

    private Button mTextChangeButton;

    private Button mViewAppearingButton;
    private View mAppearingView;

    private Button mNavigatingButton;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        mSolo = new Solo(getInstrumentation(), mActivity);

        mTextChangeButton = (Button) mActivity.findViewById(R.id.text_changing_button);

        mViewAppearingButton = (Button) mActivity.findViewById(R.id.view_appearing_button);
        mAppearingView = mActivity.findViewById(R.id.view_to_appear);

        mNavigatingButton = (Button) mActivity.findViewById(R.id.navigating_button);
    }

    public void testTextChangingButton() {
        Spoon.screenshot(mActivity, "initial_state");

        assertThat(mTextChangeButton).hasText(mActivity.getString(R.string.button_text_1));

        mSolo.clickOnView(mTextChangeButton);

        Spoon.screenshot(mActivity, "text_changed");

        mSolo.sleep(2000);

        assertThat(mTextChangeButton).hasText(mActivity.getString(R.string.button_text_2));
    }

    public void testViewVisibilityChangingButton() {

        Spoon.screenshot(mActivity, "initial_state");

        assertThat(mAppearingView).isNotVisible();

        mSolo.clickOnView(mViewAppearingButton);

        Spoon.screenshot(mActivity, "view_appeared");

        mSolo.sleep(2000);

        assertThat(mAppearingView).isVisible();

    }

    public void testNavigatingButton() {

        Spoon.screenshot(mActivity, "initial_state");

        assertTrue("Start on MainActivity", mSolo.getCurrentActivity() instanceof MainActivity);

        mSolo.clickOnView(mNavigatingButton);

        assertTrue("End on OtherActivity", mSolo.waitForActivity(OtherActivity.class));

        Spoon.screenshot(mSolo.getCurrentActivity(), "after_navigation");

        mSolo.sleep(2000);

        mSolo.getCurrentActivity().finish();

    }

    public void testDerekIsMean() {
        assertTrue(false);
    }
}
