# Uncomment the next two lines to enable the admin:
from django.contrib import admin
admin.autodiscover()
import os

urlpatterns = patterns('',
    (r'^ecomstore/$', 'ecomstore.views.home'),
)
