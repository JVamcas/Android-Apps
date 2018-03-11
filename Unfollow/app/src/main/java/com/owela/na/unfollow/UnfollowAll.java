package com.owela.na.unfollow;
/**
 * @author Petrus Kambala
 *         Electrical and Computer Engineering Student
 *         University of Cape Town
 *         on 2017/08/31.
 */

import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;
import java.util.concurrent.RecursiveTask;


class UnfollowAll extends RecursiveTask<Boolean> {
    private int lo, hi;
    private List<Long> list;
    private Twitter twitter;

    UnfollowAll(Twitter twitter, List<Long> list, int lo, int hi) {
        this.list = list;
        this.twitter = twitter;
        this.lo = lo;
        this.hi = hi;
    }
    public Boolean compute() {
        if (hi - lo <= 3) {
            try {
                for (int i = lo; i < hi; i++)
                    twitter.destroyFriendship(list.get(i));
                return true;
            } catch (TwitterException e) {
                return false;
            }
        }
        int med = (lo + hi) / 2;
        UnfollowAll left = new UnfollowAll(twitter, list, lo, med);
        UnfollowAll right = new UnfollowAll(twitter, list, med, hi);
        left.fork();
        right.fork();
        return right.join() && left.join();
    }
}
